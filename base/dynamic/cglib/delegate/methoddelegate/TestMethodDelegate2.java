package simple.cglib.delegate.methoddelegate;

import net.sf.cglib.reflect.MethodDelegate;
import simple.cglib.delegate.SampleBean;

public class TestMethodDelegate2 {
    public static void main(String[] args) {
    	SampleBean sb = new SampleBean();
    	sb.setValue("aaa");
    	BeanDelegate2 delegate = (BeanDelegate2) MethodDelegate.create(sb, "setValue", BeanDelegate2.class);
    	delegate.setValueFromDeletgate("cccccc");
    	System.out.println(sb.getValue());
	}
}
