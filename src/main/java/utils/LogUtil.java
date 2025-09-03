package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
    private static Logger logger = Logger.getLogger(LogUtil.class);

    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}