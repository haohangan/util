package org.eva.websocket.server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author guihao
 * @date 2017年1月10日下午3:09:48
 * @desc
 */
public class LoggerUtil {

	static Logger logger = Logger.getLogger(LoggerUtil.class.getName());

	static {
		logger.setLevel(Level.INFO);
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("D:/testlogger%g.log", 10000000, 11, true);
			fileHandler.setLevel(Level.INFO);
			fileHandler.setFormatter(new MyLogHander());
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void log(String msg) {
		logger.info(msg);
	}
	
	public static void severe(String msg) {
		logger.severe(msg);
	}

	static class MyLogHander extends Formatter {
		@Override
		public String format(LogRecord record) {
			Date date = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d = sd.format(date);
			return "[" + d + "]" + "[" + record.getLevel() + "]:" + record.getMessage()
					+ "\r\n";
		}
	}
}
