package org.eva.core.shareDemo;

import java.io.IOException;

import org.eva.core.cache.CommCache;
import org.eva.core.exception.ConfExcepion;
import org.eva.core.http.Req;
import org.eva.core.zk.CommZK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world!
 *
 */
@Controller
@EnableAutoConfiguration
public class App {
	@RequestMapping("/list")
	@ResponseBody
	String home(String name) {
		String rtn = null;
		try {
			String url = CommCache.getUrl("/boot","s1");
			rtn = Req.req("http://"+url+"?itemName="+name);
		} catch (IOException | ConfExcepion ex) {
			rtn = ex.getMessage();
		}
		return rtn;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		CommZK.ZKMANAGER.conn("192.168.0.112:2181");
	}
}
