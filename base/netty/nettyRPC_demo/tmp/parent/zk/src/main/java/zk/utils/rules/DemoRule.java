package zk.utils.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name="demorule")
public class DemoRule {
   
	@Condition
	public boolean when(){
		return Boolean.TRUE;
	}
	
	@Action(order=1)
    public void then(){
    	System.out.println("then...");
    }
	
	@Action(order=2)
    public void then2(){
    	System.out.println("222222");
    }
}
