package client;

import data.network.Request;
import data.network.Response;
import data.recources.*;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.Reciewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

import static client.UDPClient.commands;

public class ScriptExecutor {
    private static Stack<File> st = new Stack<>();
    public static void executeScript(String path, UDPClient udpClient) throws Exception {
        File file = new File(path);
        if (!file.canRead()) {
            System.out.println("You do not have enough rights to read the file");
            return;
        }
        if (st.isEmpty()) {
            st.add(file);
        }

        st.add(file);
        var br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        String line;
        String[] sp = new String[10];
        while ((line = br.readLine()) != null) {
            String command = line.split(" ")[0];
            Request request = new Request(line);
            if (command.equals("insert") || command.contains("remove_lower") || command.contains("replace_if_grater")
                    || command.contains("remove_grater") || command.contains("update")) {
                String key = line.split(" ")[1];
                for (int n = 0; n < 9; n++) {
                    if ((line = br.readLine()) != null) {
                        sp[n] = line;
                    }
                }
                Validator.inputIsNotEmpty(sp[0], "Name");
                Validator.coordinateXIsOk(sp[1]);
                Validator.coordinateYIsOk(sp[2]);
                Validator.startDateIsOk(sp[3]);
                Validator.salaryIsOk(sp[4]);
                Validator.endDateIsOk(sp[5]);
                Validator.statusIsOk(sp[6]);
                Validator.annualTurnoverIsOk(sp[7]);
                Validator.organizationTypeIsOk(sp[8]);

                Worker worker = new Worker();
                worker.setName(sp[0]);
                Coordinates coordinates = new Coordinates(Long.parseLong(sp[1]), Integer.parseInt(sp[2]));
                worker.setCoordinates(coordinates);
                worker.setCreationDate(ZonedDateTime.now());
                worker.setStartDate(LocalDate.parse(sp[3]));
                worker.setSalary(Long.parseLong(sp[4]));

                LocalDate localDate = LocalDate.parse(sp[5], DateTimeFormatter.ISO_LOCAL_DATE);
                ZoneId zoneId = ZoneId.systemDefault();
                worker.setEndDate(localDate.atStartOfDay(zoneId));

                worker.setStatus(Status.valueOf(sp[6]));

                Organization og = new Organization();
                og.setAnnualTurnover(Double.parseDouble(sp[7]));
                og.setType(OrganizationType.valueOf(sp[8]));
                worker.setOrganization(og);

                request.setBaseCommand(commands.get(command));
                request.setWorker(worker);
                udpClient.sendRequest(request);
                System.out.println("Отправлено");
                Response response = udpClient.getResponse();
                System.out.println("Получено:\n" + response.getMessage());
            } else {
                if (line.contains("execute_script")) {
                    File file_new = new File(line.split(" ")[1]);
                    if (!file_new.canRead()) {
                        System.out.println("You do not have enough rights to read the file");
                    }
                    if (st.contains(file_new)) {
                        System.out.println("Recursion to file " + file.getName() + " was skipped");
                    } else {
                        try {
                            request.setBaseCommand(commands.get(command));
                            udpClient.sendRequest(request);
                            System.out.println("Отправлено");
                            Response response = udpClient.getResponse();
                            System.out.println("Получено:\n" + response.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                } else {
                    try {
                        request.setBaseCommand(commands.get(command));
                        udpClient.sendRequest(request);
                        System.out.println("Отправлено");
                        Response response = udpClient.getResponse();
                        System.out.println("Получено:\n" + response.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        st.pop();
    }
}
