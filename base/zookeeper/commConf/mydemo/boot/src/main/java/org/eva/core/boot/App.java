package org.eva.core.boot;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.eva.core.cache.CommCache;
import org.eva.core.zk.CommZK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

/**
 * start
 * 
 * @Bean // public EmbeddedServletContainerCustomizer containerCustomizer() { //
 *       return (container -> { // container.setPort(80); // }); // }
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		Map<String, String> map = new HashMap<>();
		map.put(CommCache.IP, "127.0.0.1");
		map.put(CommCache.PORT, "80");
		map.put("s1", "/search");
		CommZK.ZKMANAGER.conn("192.168.0.112:2181");
		Gson gson = new Gson();
		CommZK.ZKMANAGER.getManager().createEphemeral("/boot", gson.toJson(map).getBytes(StandardCharsets.UTF_8));
	}
}
