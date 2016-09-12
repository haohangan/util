package zk.utils.vo;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 
 * 节点信息<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class NodeInfo {
	private String name;
	private Map<String, String> map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	/**
	 * 节点添加信息
	 * @param key
	 * @param value
	 */
	public void addInfo(String key, String value) {
		if (map != null) {
			map.put(key, value);
			return;
		}
		map = new HashMap<>();
		map.put(key, value);
	}

	public byte[] toJson() {
		Gson gson = new Gson();
		return gson.toJson(map).getBytes(StandardCharsets.UTF_8);
	}
}
