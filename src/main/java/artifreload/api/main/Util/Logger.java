package artifreload.api.main.Util;

import artifreload.api.main.ArtifactReloadedAPI;
import org.apache.logging.log4j.Level;


@UtilityClass
public final class Logger {

    private static final org.apache.logging.log4j.Logger LOGGER;

    static {
        LOGGER = org.apache.logging.log4j.LogManager.getLogger(ArtifactReloadedAPI.Artifact_ID.toUpperCase());
    }

    private Logger() {

    }

    public static void artifactDebug(String format, Object... data) {
        if (ArtifactReloadedAPI.config().ENABLE_DEBUGGING.get()) {
            LOGGER.log(Level.DEBUG, format, data);
        }
    }

    public static void debug(String format, Object... data) {

        LOGGER.log(Level.DEBUG, format, data);
    }

    public static void trace(String format, Object... data) {

        LOGGER.log(Level.TRACE, format, data);
    }

    public static void info(String format, Object... data) {

        LOGGER.log(Level.INFO, format, data);
    }

    public static void error(String format, Object... data) {

        LOGGER.log(Level.ERROR, format, data);
    }

    public static void fatal(String format, Object... data) {

        LOGGER.log(Level.FATAL, format, data);
    }

}
