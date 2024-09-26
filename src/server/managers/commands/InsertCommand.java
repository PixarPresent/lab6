package server.managers.commands;

import data.network.Request;
import data.recources.Worker;
import server.managers.Reciewer;
import server.exceptions.WrongArgumentException;
import server.system.TextColor;
/**
 * Данная команда добавляет новый элемент по ключу
 *
 * @author Konstantin
 * @see BaseCommand
 * @see Worker
 * @since 1.0
 */
public class InsertCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws WrongArgumentException {
        return Reciewer.insertNew(request);
    }

    @Override
    public String getName() {
        return "insert null {element}";
    }

    @Override
    public String getDescription() {
        return "insert new element";
    }
}
