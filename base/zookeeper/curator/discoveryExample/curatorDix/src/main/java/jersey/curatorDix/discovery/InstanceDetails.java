package jersey.curatorDix.discovery;
/**
 * 细节
 */
public class InstanceDetails {
    
	private String description;
	
	public InstanceDetails(){
		this("");
	}
	
	public InstanceDetails(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
