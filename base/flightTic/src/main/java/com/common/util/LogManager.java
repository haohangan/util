package com.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月23日 下午3:45:10 类说明
 */
public class LogManager {
	// 初始化LogManager
	static {
		// 读取配置文件
		String fileName = "logger.properties";//"logger.properties"
		ClassLoader cl = LogManager.class.getClassLoader();
		InputStream inputStream = null;
		if (cl != null) {
			inputStream = cl.getResourceAsStream(fileName);
		} else {
			inputStream = ClassLoader.getSystemResourceAsStream(fileName);
		}
		java.util.logging.LogManager logManager = java.util.logging.LogManager
				.getLogManager();
		try {
			// 重新初始化日志属性并重新读取日志配置。
			logManager.readConfiguration(inputStream);
		} catch (SecurityException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * 获取日志对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz.getName());
		return logger;
	}

	public static void main(String[] args) {
		Logger log = LogManager.getLogger(LogManager.class);
		log.info("啥");
	}
}
