package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда выводит список всех команд
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class ShowCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
       return Reciewer.showCommand();

    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show data";
    }
}
