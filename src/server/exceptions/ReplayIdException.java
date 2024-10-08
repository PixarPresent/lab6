package server.exceptions;

import server.system.TextColor;

/**
 * Обеспечивает исключение, если такой ID уже существует
 *
 * @author Konstantin
 * @since 1.0
 */
public class ReplayIdException extends Exception {
    /**
     * @param id ID которое уже используется
     * @since 1.0
     */
    public ReplayIdException(long id) {
        super(TextColor.ANSI_RED + "ID " + id + " is already used" + TextColor.ANSI_RESET);
    }

    public ReplayIdException(String id) {
        super(TextColor.ANSI_RED + "ID " + id + " is already used" + TextColor.ANSI_RESET);
    }
}
