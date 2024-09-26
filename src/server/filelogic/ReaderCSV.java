package server.filelogic;

import data.recources.*;
import server.exceptions.ReadFileException;
import server.exceptions.RootException;
import server.managers.CollectionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Данный класс выполняет чтение данных, которые хранятся в формате CSV
 *
 * @author Konstantin
 * @since 1.0
 */
public class ReaderCSV {
    /**
     * Читает данные из файла в коллекцию
     *
     * @param path путь до файла
     * @see CollectionManager
     */
    public static void read(String path) throws RootException, ReadFileException {
        File file = new File(path);
        if (!file.canRead()) {
            throw new RootException("You do not have enough rights to read the file");
        }
        try {
            var br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                String[] sp = line.split(";");
                Validator.idIsOk(sp[1]);
                Validator.inputIsNotEmpty(sp[2], "Name");
                Validator.coordinateXIsOk(sp[3]);
                Validator.coordinateYIsOk(sp[4]);
                Validator.startDateIsOk(sp[5]);
                Validator.salaryIsOk(sp[6]);
                Validator.startDateIsOk(sp[7]);
                Validator.endDateIsOk(sp[8]);
                Validator.statusIsOk(sp[9]);
                Validator.annualTurnoverIsOk(sp[10]);
                Validator.organizationTypeIsOk(sp[11]);

                Worker worker = new Worker();
                worker.setId(Integer.parseInt(sp[1]));

                worker.setName(sp[2]);
                Coordinates coordinates = new Coordinates(Long.parseLong(sp[3]), Integer.parseInt(sp[4]));
                worker.setCoordinates(coordinates);

                LocalDate localDate = LocalDate.parse(sp[5], DateTimeFormatter.ISO_LOCAL_DATE);
                ZoneId zoneId = ZoneId.systemDefault();
                worker.setCreationDate(localDate.atStartOfDay(zoneId));

                worker.setSalary(Long.parseLong(sp[6]));
                worker.setStartDate(LocalDate.parse(sp[7]));

                localDate = LocalDate.parse(sp[8], DateTimeFormatter.ISO_LOCAL_DATE);
                worker.setEndDate(localDate.atStartOfDay(zoneId));

                worker.setStatus(Status.valueOf(sp[9]));

                Organization og = new Organization();
                og.setAnnualTurnover(Double.parseDouble(sp[10]));
                og.setType(OrganizationType.valueOf(sp[11]));
                worker.setOrganization(og);

                CollectionManager.add(sp[0], worker);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ReadFileException();
        }
    }
}
