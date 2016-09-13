package log;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum CommonLogger {
	INSTANCE;

	public static final Logger LOG = Logger.getGlobal();

	static {
		LOG.setLevel(Level.INFO);
	}

	public static void info(String msg) {
		LOG.info(msg);
	}
}
