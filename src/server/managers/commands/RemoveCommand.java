package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда удаляет из коллекции элемент по ключу
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class RemoveCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws WrongArgumentException {
        return Reciewer.removeByKey(request);
    }

    @Override
    public String getName() {
        return "remove_key null";
    }

    @Override
    public String getDescription() {
        return "remove_key element by key";
    }
}
