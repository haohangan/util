package com.common.util;

import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月26日 下午3:56:41 类说明
 */
public class JsFileReaderUtil {
	static ScriptEngineManager manager = new ScriptEngineManager();
	static ScriptEngine engine = manager.getEngineByName("javascript");

	public static void main(String[] args) throws ScriptException,
			NoSuchMethodException, IOException {
		String jsFileName = "C:\\Users\\Administrator\\Desktop\\华夏\\sec.js";
		FileReader reader = new FileReader(jsFileName);
		engine.eval(reader);

		if (engine instanceof Invocable) {
			Invocable invoke = (Invocable) engine; // 调用merge方法，并传入两个参数

			// var key = RSAUtils.getKeyPair($("#empoent").val(), '',
			// $("#module").val());
			// password = RSAUtils.encryptedString(key, $("#password1").val());
			// c = merge(2, 3);

			String key = (String) invoke.invokeFunction("RSAUtils.getKeyPair",
					2, 3);
			String password = (String) invoke.invokeFunction(
					"RSAUtils.encryptedString", key, "123456");
			System.out.println(password);
		}

		reader.close();
	}
}
