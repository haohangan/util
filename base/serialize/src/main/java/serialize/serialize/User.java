package serialize.serialize;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -7573846668952354604L;
	private int id;
	private String name;
	private char sex;

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

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public User(int id, String name, char sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}

}
