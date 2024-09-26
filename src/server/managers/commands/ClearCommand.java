package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда очищает коллекцию
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class ClearCommand implements BaseCommand, Serializable {

    @Override
    public String execute(Request request) throws Exception {
        Reciewer.clearMap();
        return "Коллекция очищена";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear data from table";
    }
}
