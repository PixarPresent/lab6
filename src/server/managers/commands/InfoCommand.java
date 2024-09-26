package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда выводит данные о программе
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class InfoCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.showInfo();
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "information about app";
    }
}
