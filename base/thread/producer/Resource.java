package ti2.producer;

/**
 * 资源类
 * 
 * @author Administrator
 *
 */
public class Resource {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Resource(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "资源：[id=" + id + "]";
	}

}
