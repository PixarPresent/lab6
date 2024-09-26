package client;

import data.network.Request;
import data.network.Response;
import data.recources.Worker;
import server.exceptions.BuildOrganizationException;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;
import server.managers.commands.*;
import server.managers.generators.WorkerGenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public class UDPClient {

    private final InetSocketAddress serverAddress;
    private final DatagramChannel channel;
    public static LinkedHashMap<String, BaseCommand> commands;
    private DatagramSocket socket = new DatagramSocket();

    public UDPClient(String hostname, int port) throws IOException {
        this.serverAddress = new InetSocketAddress(InetAddress.getByName(hostname), port);
        this.channel = DatagramChannel.open();
        this.channel.configureBlocking(false);

        commands = new LinkedHashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("insert", new InsertCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_key", new RemoveCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCSVCommand());
        commands.put("remove_greater", new RemoveLowerCommand());
        commands.put("history", new HistoryCommand());
        commands.put("replace_if_grater", new ReplaceIfGraterCommand());
        commands.put("remove_lower_key ", new RemoveLowerKeyCommand());
        commands.put("min_by_id", new ShowMinByIdCommand());
        commands.put("count_greater_than_status ", new CountGreaterThanStatusCommand());
        commands.put("print_unique_salary  ", new PrintUniqueSalary());
    }

    public void sendRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        DatagramPacket packet = new DatagramPacket(buffer.array(), byteArrayOutputStream.size(), serverAddress);
        socket.send(packet);
    }

    public Response getResponse() throws IOException, ClassNotFoundException {
        byte[] bufferReader = new byte[10000];
        DatagramPacket packetGet = new DatagramPacket(bufferReader, bufferReader.length);

        socket.setSoTimeout(3000);
        socket.receive(packetGet);

        ByteBuffer bg = ByteBuffer.wrap(packetGet.getData());
        bg.flip();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bg.array());
        ObjectInputStream oi = new ObjectInputStream(byteArrayInputStream);
        return (Response) oi.readObject();
    }


    public void close() throws IOException {
        channel.close();
    }

    public void start() throws InterruptedException {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                String inputcommand = input.split(" ")[0];
                if (input.contains("execute_script")) {
                    ScriptExecutor.executeScript(input.split(" ")[1], this);
                    continue;
                }
                if (inputcommand.equals("exit")) {
                    System.exit(0);
                }
                if (commands.keySet().contains(inputcommand)) {
                    Request request = new Request(input);
                    if (input.contains("insert") || input.contains("update") || input.contains("remove_greater") ||
                            input.contains("replace_if_greater") || input.contains("remove_lower")) {
                        Worker worker = WorkerGenerator.createWorker();
                        request.setWorker(worker);
                    }

                    try {
                        request.setBaseCommand(commands.get(inputcommand));
                        sendRequest(request);
                        System.out.println("Отправлено");
                        Response response = getResponse();
                        System.out.println("Получено:\n" + response.getMessage());

                    } catch (Exception e) {
                        System.out.println("Сервер временно не доступен, повторите попытку позже.");
                    }
                } else {
                    System.out.println("Команда не найдена.");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
