package server.system;

import java.io.File;
import java.io.IOException;

/**
 * Главный класс, который запускает программу.
 * Требуется сохранить путь до CSV файла в переменную окружения
 *
 * @author Konstantin
 * @since 1.0
 */
public class Main {
    /**
     * Точка начала программы.
     */
    public static String data_path = "";
    public static void main(String[] args) {

        
        data_path = "/home/pixar/lab6/src/server/system/data.csv";
        if (data_path == null) {
            System.out.println(TextColor.ANSI_RED + "Something wrong (no environment variable)" + TextColor.ANSI_RESET);
        } else {
		 Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    WriterCSV.write(data_path);
                } catch (IOException | RootException e) {
                    System.out.println(e.getMessage());
                }
            }));

            File file = new File(data_path);
            String[] pathLink = data_path.split("\\.");
            if (file.canRead() && file.canWrite() && pathLink[pathLink.length - 1].equals("csv")) {
		ReaderCSV.read(data_path);
                UDPServer server = null;
                try {
                    server = new UDPServer(8080);
                    server.start();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


            } else if (!file.canRead() || !file.canWrite()) {
                System.out.println(TextColor.ANSI_RED + "You do not have enough root" + TextColor.ANSI_RESET);
            } else {
                System.out.println(TextColor.ANSI_RED + "Something wrong with file" + TextColor.ANSI_RESET);
            }
        }
    }
}
