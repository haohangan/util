package zk.utils.vo;

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

	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
