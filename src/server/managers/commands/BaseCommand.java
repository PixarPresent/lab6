package server.managers.commands;

import data.network.Request;
import data.network.Response;

import java.awt.geom.RectangularShape;

/**
 * Базовый интерфейс для реализации команд. Такие команды содержаться в CollectionManager
 *
 * @author vnikolaenko
 * @see server.managers.CollectionManager
 * @since 1.0
 */
public interface BaseCommand {
    /**
     * Базовый метод для вывода исполнения команды
     * Выбрасывает ошибки при реализации
     *
     * @author Konstantin
     * @since 1.0
     */
    public String execute(Request request) throws Exception;

    /**
     * Базовый метод для вывода названия команды
     *
     * @author Konstantin
     * @since 1.0
     */
    public String getName();

    /**
     * Базовый метод для вывода описания команды
     *
     * @author Konstantin
     * @since 1.0
     */
    public String getDescription();
}
