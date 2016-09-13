package zk.utils.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "demorule")
public class DemoRule {

	private String input;

	@Condition
	public boolean when() {
		if ("yes".equals(input)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Action(order = 0)
	public void then() {
		System.out.println("then...");
	}

	public void setInput(String input) {
		this.input = input;
	}
}
