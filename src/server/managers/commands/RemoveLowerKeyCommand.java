package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда удаляет из коллекции все элементы, ключ которых меньше, чем заданный
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class RemoveLowerKeyCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.removeLowerKey(request.getMessage().split(" ")[1]);
    }

    @Override
    public String getName() {
        return "replace_lower_key";
    }

    @Override
    public String getDescription() {
        return "replace element with lower key";
    }
}
