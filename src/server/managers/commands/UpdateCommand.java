package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда обновляет элемент коллекции по ID
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class UpdateCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.updateById(request.getMessage().split(" ")[1], request.getWorker());
    }

    @Override
    public String getName() {
        return "update id {element}";
    }

    @Override
    public String getDescription() {
        return "update element";
    }
}
