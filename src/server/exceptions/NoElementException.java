package server.exceptions;

import server.system.TextColor;
/**
 * Обеспечивает исключение, если не существует элемента в коллекции
 *
 * @see server.managers.CollectionManager
 * @author Konstantin
 * @since 1.0
 */
public class NoElementException extends Exception{
    /**
     * Исключение по ключу
     *
     * @param key ключ элемента
     * @since 1.0
     */
    public NoElementException(String key){
        super(TextColor.ANSI_RED + "No element in collection with key: " + key + TextColor.ANSI_RESET);
    }
    /**
     * Конструктор с ID
     *
     * @param id ID элемента
     * @author vnikolaenko
     * @since 1.0
     */
    public NoElementException(Long id){
        super(TextColor.ANSI_RED + "No element in collection with id: " + id + TextColor.ANSI_RESET);
    }
}
