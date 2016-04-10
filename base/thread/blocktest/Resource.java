package ti2.blocktest;

public class Resource {
	private Integer id;

	public Resource(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "资源：" + id;
	}
}
