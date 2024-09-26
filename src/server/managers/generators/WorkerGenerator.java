package server.managers.generators;

import data.recources.*;
import server.exceptions.BuildOrganizationException;
import server.exceptions.WrongArgumentException;
import server.system.TextColor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WorkerGenerator {
    public static Worker createWorker() throws WrongArgumentException, BuildOrganizationException {
        System.out.println(TextColor.ANSI_BLUE + "Welcome to Worker Builder." + TextColor.ANSI_RESET);

        String input, a, b;

        Scanner scanner = new Scanner(System.in);

        Worker worker = new Worker();
        /*
        if (id == 0) {
            organization = new Organization();
        } else {
            organization = new Organization(id);
        }

         */

        while (true) {
            try {
                System.out.print("Input name (" + TextColor.ANSI_PURPLE + "String" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.inputIsNotEmpty(input, "NAME");
                worker.setName(input);
                break;

            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Input x (" + TextColor.ANSI_PURPLE + "Long" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.coordinateXIsOk(input);
                a = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Input y (" + TextColor.ANSI_PURPLE + "Integer" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    b = null;
                    break;
                }
                Validator.coordinateYIsOk(input);
                b = input;
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Coordinates coordinates = new Coordinates(Long.parseLong(a), Integer.parseInt(b));
        worker.setCoordinates(coordinates);

        while (true) {
            try {
                System.out.print("Input salary (" + TextColor.ANSI_PURPLE + "Long" + TextColor.ANSI_RESET + ") > 0: ");
                input = scanner.nextLine();
                Validator.salaryIsOk(input);
                worker.setSalary(Long.parseLong(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


        while (true) {
            try {
                System.out.print("Input startDate (" + TextColor.ANSI_PURPLE + "YYYY-MM-DD" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.startDateIsOk(input);
                worker.setStartDate(LocalDate.parse(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Input endDate (" + TextColor.ANSI_PURPLE + "YYYY-MM-DD" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.endDateIsOk(input);

                LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                ZoneId zoneId = ZoneId.systemDefault();
                worker.setEndDate(localDate.atStartOfDay(zoneId));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Input Status (" + TextColor.ANSI_PURPLE + "FIRED,\n" +
                        "    HIRED,\n" +
                        "    RECOMMENDED_FOR_PROMOTION,\n" +
                        "    REGULAR" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.statusIsOk(input);
                worker.setStatus(Status.valueOf(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Organization og = new Organization();

        while (true) {
            try {
                System.out.print("Input annualTurnover (" + TextColor.ANSI_PURPLE + "Double" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.annualTurnoverIsOk(input);
                og.setAnnualTurnover(Double.parseDouble(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Input OrganizationType  (" + TextColor.ANSI_PURPLE + "COMMERCIAL,\n" +
                        "    PUBLIC,\n" +
                        "    OPEN_JOINT_STOCK_COMPANY;" + TextColor.ANSI_RESET + "): ");
                input = scanner.nextLine();
                Validator.organizationTypeIsOk(input);
                og.setType(OrganizationType.valueOf(input));
                break;
            } catch (WrongArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        worker.setOrganization(og);
        System.out.println("Generating ...");
        return worker;
    }
}
