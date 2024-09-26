package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда выводит последние 6 команд без ключей
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class HistoryCommand implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.showHistory();
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescription() {
        return "show last 6 command";
    }
}
