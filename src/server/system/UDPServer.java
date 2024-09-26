package server.system;

import data.network.Request;
import data.network.Response;
import server.managers.CommandManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class UDPServer {

    private final DatagramChannel channel;
    private InetSocketAddress clientAddress;
    private final int port;

    public UDPServer(int port) throws IOException {
        this.port = port;
        this.channel = DatagramChannel.open();
        this.channel.configureBlocking(false);
        this.channel.bind(new InetSocketAddress(port));
        new CommandManager();
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.println("Сервер запущен на порту: " + port);
        while (true) {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(10000);
                clientAddress = (InetSocketAddress) channel.receive(buffer);
                buffer.flip();

                if (clientAddress != null) {
                    Request request = getRequest(buffer);
                    Response answer = execute(request);
                    sendResponse(answer);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public Request getRequest(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        System.out.println("Получен запрос от клиента " + clientAddress);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream oi = new ObjectInputStream(byteArrayInputStream);
        return (Request) oi.readObject();
    }

    public Response execute(Request request) {
        return CommandManager.startExecuting(request);
    }

    public void sendResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);

        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        channel.send(buffer, clientAddress);

        System.out.println("Отправлен ответ клиенту " + clientAddress);
    }


    public void close() throws IOException {
        channel.close();
    }
}
