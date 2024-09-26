package server.exceptions;

import data.recources.Worker;
import server.system.TextColor;

/**
 * Обеспечивает исключение, если возникла ошибка при создании объекта класса Organization
 *
 * @see Worker
 * @author Konstantin
 * @since 1.0
 */
public class BuildOrganizationException extends Exception{
    /**
     * @param message сообщение, которое необходимо вывести
     * @since 1.0
     */
    public BuildOrganizationException(String message){
        super(TextColor.ANSI_RED + message + TextColor.ANSI_RESET);
    }
}
