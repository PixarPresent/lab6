package server.managers;

import data.recources.Worker;
import server.managers.generators.IdGenerator;
import server.exceptions.NoElementException;

import java.time.LocalDate;
import java.util.LinkedHashMap;

/**
 * Данный класс отвечает за взаимодействие с коллекцией
 * Содержит коллекцию команд
 *
 * @author Konstantin
 * @see server.managers.commands.BaseCommand
 * @see Worker
 * @since 1.0
 */
public class CollectionManager {
    private static CollectionManager instance;
    private static LinkedHashMap<String, Worker> table = new LinkedHashMap<>();
    private static LocalDate date = LocalDate.now();

    /**
     * Базовый конструктор
     */
    private CollectionManager() {
        table = new LinkedHashMap<>();
        date = LocalDate.now();
    }

    public static synchronized CollectionManager getInstance() {
        if (instance == null) {
            instance = new CollectionManager();
        }
        return instance;
    }

    /**
     * Получить дату инициализации коллекции
     *
     * @return дата инициализации
     */
    public static LocalDate getInitDate() {
        return date;
    }


    /**
     * Добавить новую организацию по ключу
     *
     * @param key ключ
     * @param worker организация
     */
    public static void add(String key, Worker worker) {
        if (table == null) {
            table = new LinkedHashMap<>();
        }
        table.put(key, worker);
        IdGenerator.add(worker.getId());
    }

    /**
     * Удалить новую организацию по ключу
     *
     * @param key ключ
     */
    public static String remove(String key) throws NoElementException {
        if (table == null || !CollectionManager.table.containsKey(key)) {
            throw new NoElementException(key);
        } else {
            IdGenerator.remove(CollectionManager.table.get(key).getId());
            table.remove(key);
            return "Работник был удален";
        }

    }

    /**
     * Получить коллекцию
     *
     * @return коллекция
     */
    public static LinkedHashMap<String, Worker> getTable() {
        return table;
    }
}
