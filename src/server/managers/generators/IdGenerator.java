package server.managers.generators;

import java.lang.Math;
import java.util.ArrayList;

/**
 * Класс генерирует ID для объекта Organization
 *
 * @author Konstantin
 * @since 1.0
 */
public class IdGenerator {
    private static IdGenerator instance;
    private static final Integer min = 1000000;
    private static final Integer max = 10000000;
    private static ArrayList<Integer> IdList = new ArrayList<>();

    /**
     * Базовый конструктор
     *
     * @author vnikolaenko
     * @since 1.0
     */
    private IdGenerator() {
        IdList = new ArrayList<>();
    }
    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    /**
     * Метод генерирует новый уникальный ID
     *
     * @author Konstantin
     * @since 1.0
     */
    public static int generateId() {
        int id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        while (IdList.contains(id)) {
            id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        }
        IdList.add(id);
        return id;
    }

    /**
     * Метод проверяет ID на уникальность
     *
     * @param id какой id нужно проверить на уникальность
     * @author vnikolaenko
     * @since 1.0
     */
    public static boolean idIsUnique(long id) {
        if (IdList.contains(id)) {
            return false;
        }
        return true;
    }

    /**
     * Добавляет ID в список
     *
     * @param id какой id нужно добавить
     * @author vnikolaenko
     * @since 1.0
     */
    public static void add(int id) {
        IdList.add(id);
    }

    /**
     * Удаляет ID из списка
     *
     * @param id какой id нужно удалить
     * @author vnikolaenko
     * @since 1.0
     */
    public static void remove(long id) {
        IdList.remove(id);
    }
    public static void clear(){
        IdList.clear();
    }
}
