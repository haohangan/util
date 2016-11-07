package org.eva.core.cache;

import java.util.Map;

class ConfNode {
	static final String point = ":";

	private String name;
	private String ip;
	private String port;
	private Map<String, String> map;
	
	public ConfNode(Map<String, String> map) {
		super();
		this.map = map;
		this.ip = map.get(CommCache.IP);
		this.port = map.get(CommCache.PORT);
	}

	public ConfNode() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String loadUrl(String urlKey) {
		String url = map.get(urlKey);
		return new StringBuffer(ip).append(point).append(port).append(url).toString();
	}
}
