package simple.cglib.delegate.multicastdelegate;

public class SimpleMulticastBean implements DelegateProvider {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
