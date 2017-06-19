package simple.cglib.delegate.methoddelegate;

import net.sf.cglib.reflect.MethodDelegate;
import simple.cglib.delegate.SampleBean;

public class TestMethodDelegate {
    public static void main(String[] args) {
    	SampleBean sb = new SampleBean();
    	sb.setValue("aaa");
    	BeanDelegate delegate = (BeanDelegate) MethodDelegate.create(sb, "getValue", BeanDelegate.class);
    	System.out.println(delegate.getValueFromDelegate());
	}
}
