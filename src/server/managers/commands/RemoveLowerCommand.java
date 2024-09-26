package server.managers.commands;

import data.network.Request;
import server.exceptions.BuildOrganizationException;
import server.exceptions.NoElementException;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда удаляет из коллекции все элементы, превышающие заданный
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class RemoveLowerCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws WrongArgumentException, BuildOrganizationException, NoElementException {
        return Reciewer.removeLower();
    }

    @Override
    public String getName() {
        return "remove_grater {element}";
    }

    @Override
    public String getDescription() {
        return "remove elements with grater than the element";
    }
}
