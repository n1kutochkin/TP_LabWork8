package ru.mai;

import java.io.IOException;
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
    public Logger logger;

    public MyLogger(String name) {
        logger = getMyLogger(name);
    }

    /**
     * Создает логгер для какого-то процесса с созданием файла логирования для этого процесса.
     *
     * @param name название процесса
     *
     * @return логгер этого процесса
     */
    public static Logger getMyLogger(String name) {
        Logger logger = Logger.getLogger(name);

        try {
            FileHandler fileHandler = new FileHandler(String.valueOf(new StringBuilder().append(SYSTEM).append(LOG_EXT)));
            fileHandler.setFormatter(new SimpleFormatter());
            sysLogger.addHandler(fileHandler);

            fileHandler = new FileHandler(String.valueOf(new StringBuilder().append(name).append(LOG_EXT)));
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

    public void log(Level warning, String message) {
        logger.log(warning, message);
    }
}
