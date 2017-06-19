package simple.cglib.delegate.constructordelegate;

import net.sf.cglib.reflect.ConstructorDelegate;
import simple.cglib.delegate.SampleBean;

public class ConstructorDelegateTest {

	public static void main(String[] args) {
		SampleBeanConstructorDelegate constructorDelegate =  (SampleBeanConstructorDelegate) ConstructorDelegate.create(SampleBean.class, SampleBeanConstructorDelegate.class);
		SampleBean bean = (SampleBean) constructorDelegate.newInstance();
		System.out.println(bean.getClass());
		System.out.println(SampleBean.class.isAssignableFrom(bean.getClass()));
	}
}
