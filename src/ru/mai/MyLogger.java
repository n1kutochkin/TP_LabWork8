package ru.mai;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Класс создания логирования
 *
 * @author n1kutochkin
 */
public class MyLogger {

    private static final String SYSTEM = "system";
    private static final String LOG_EXT = ".log";
    public static final Logger sysLogger = Logger.getLogger(SYSTEM);
    private Logger logger;
    private static Optional<Exception> sysLoggerExceptionHandler = Optional.empty();

    static {
        try {
            FileHandler fileHandler = new FileHandler(String.valueOf(new StringBuilder().append(SYSTEM).append(LOG_EXT)));
            fileHandler.setFormatter(new SimpleFormatter());
            sysLogger.addHandler(fileHandler);
        } catch (Exception e) {
            sysLoggerExceptionHandler = Optional.of(e);
        }
    }

    public MyLogger(String name) throws SysLoggerIsNotAvailableException {
        logger = getMyLogger(name);
    }

    /**
     * Создает логгер для какого-то процесса с созданием файла логирования для этого процесса.
     *
     * @param name название процесса
     *
     * @return логгер этого процесса
     */
    public static Logger getMyLogger(String name) throws SysLoggerIsNotAvailableException {
        Logger logger = Logger.getLogger(name);

        if (sysLoggerExceptionHandler.isPresent()) {
            throw new SysLoggerIsNotAvailableException(name, sysLoggerExceptionHandler.get().getMessage());
        } else {

            try {
                FileHandler fileHandler = new FileHandler(String.valueOf(new StringBuilder().append(name).append(LOG_EXT)));
                logger.addHandler(fileHandler);
            } catch (SecurityException e) {
                sysLogger.log(Level.SEVERE,
                        "Не удалось создать файл лога из-за политики безопасности.",
                        e);
            } catch (IOException e) {
                sysLogger.log(Level.SEVERE,
                        "Не удалось создать файл лога из-за ошибки ввода-вывода.",
                        e);
            }

            return logger;
        }
    }

    public void log(Level warning, String message) {
        logger.log(warning, message);
    }

    private static class SysLoggerIsNotAvailableException extends Exception {

        SysLoggerIsNotAvailableException(String whereIs, String reason) {
            super("Произошла ошибка при создании логгера системы. Название логгера, который создавался: " + whereIs
                    + "\t" + reason);
        }
    }
}
