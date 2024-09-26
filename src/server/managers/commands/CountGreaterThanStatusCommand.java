package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда выводит количество элементов, значение поля status которых больше заданного
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class CountGreaterThanStatusCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.countGreaterThanStatus(request.getMessage().split(" ")[1]);
    }

    @Override
    public String getName() {
        return "count_greater_than_status";
    }

    @Override
    public String getDescription() {
        return "display the number of elements whose status field value is greater than a given one";
    }
}
