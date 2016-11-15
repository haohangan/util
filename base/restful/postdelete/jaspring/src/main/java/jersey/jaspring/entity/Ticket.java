package jersey.jaspring.entity;

import java.util.Date;

import javax.ws.rs.FormParam;

public class Ticket {
	
	@FormParam("id")
	private int id;
	
	@FormParam("name")
	private String name;
	private Date date;

	public Ticket(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.date = new Date();
	}

	public Ticket() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", name=" + name + ", date=" + date + "]";
	}

}
