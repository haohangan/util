package jersey.jaspring.resources.reqvo;

import org.hibernate.validator.constraints.NotBlank;

public class ReqVO {
   
	@NotBlank(message="不能为空的name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ReqVO [name=" + name + "]";
	}
}
