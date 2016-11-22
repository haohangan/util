package jersey.simple_server.discovery;

/**
 * 服务实例的内容
 * 
 * @author YKSE
 *
 */
public class InstanceDetails {
	private String name;
	private String type;// 类型
	private String description;
	private int weight;// 权重

	public InstanceDetails(String name,String type, String description, int weight) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
		this.weight = weight;
	}
	
	public InstanceDetails(String name,String type, String description) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
	}

	public InstanceDetails() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
