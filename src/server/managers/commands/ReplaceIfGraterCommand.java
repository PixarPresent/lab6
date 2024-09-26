package server.managers.commands;

import data.network.Request;
import server.exceptions.BuildOrganizationException;
import server.exceptions.NoElementException;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда заменяет значение по ключу, если новое значение больше старого
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class ReplaceIfGraterCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws WrongArgumentException, BuildOrganizationException, NoElementException {
        return Reciewer.replaceIfGrater(request.getMessage().split(" ")[1], request.getWorker());
    }

    @Override
    public String getName() {
        return "replace_if_greater";
    }

    @Override
    public String getDescription() {
        return "null {element} - update element by key if new bigger than element in collection with the same key";
    }
}
