package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда сохраняет коллекцию в формате XML
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class SaveCSVCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        Reciewer.saveData();
        return "";
    }


    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save data to XML file (path)";
    }
}
