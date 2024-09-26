package server.managers.commands;

import data.network.Request;
import server.exceptions.WrongArgumentException;
import server.managers.Reciewer;

import java.io.Serializable;

/**
 * Данная команда выводит уникальные значения поля salary всех элементов в коллекции
 *
 * @author Konstantin
 * @see BaseCommand
 * @since 1.0
 */
public class PrintUniqueSalary implements BaseCommand, Serializable {
    @Override
    public String execute(Request request) throws Exception {
        return Reciewer.printUniqueSalary();
    }

    @Override
    public String getName() {
        return "print_unique_salary";
    }

    @Override
    public String getDescription() {
        return "print the unique values of the salary field of all elements in the collection";
    }
}
