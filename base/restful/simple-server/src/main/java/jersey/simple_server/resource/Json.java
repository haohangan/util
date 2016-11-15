package jersey.simple_server.resource;

import java.util.Date;

public class Json {
	private String name;
	private Date date;

	public Json() {
		super();
	}

	public Json(String name) {
		super();
		this.name = name;
		this.date = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
