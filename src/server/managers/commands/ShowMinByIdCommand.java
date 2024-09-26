package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда выводит элемент с минимальным ID
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class ShowMinByIdCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.showMinById();
    }

    @Override
    public String getName() {
        return "min_by_id";
    }

    @Override
    public String getDescription() {
        return "show element with the less ID";
    }
}
