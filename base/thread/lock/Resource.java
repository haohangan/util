package current.cp;

/**
 * ��Դid
 * 
 * @author 976175665
 * @date 2017��2��20�� ����11:36:24
 */
public class Resource {

	private String name;

	public Resource(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Resource [name=" + name + "]";
	}

}
