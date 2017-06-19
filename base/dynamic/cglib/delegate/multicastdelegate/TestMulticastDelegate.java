package simple.cglib.delegate.multicastdelegate;

import net.sf.cglib.reflect.MulticastDelegate;

public class TestMulticastDelegate {
	public static void main(String[] args) {
		MulticastDelegate md = MulticastDelegate.create(DelegateProvider.class);
		SimpleMulticastBean bean1 = new SimpleMulticastBean();
		SimpleMulticastBean bean2 = new SimpleMulticastBean();
		 md = md.add(bean1);
		 md = md.add(bean2);

//		md.add(bean1);
//		md.add(bean2);

		DelegateProvider dp = (DelegateProvider) md;
		dp.setValue("aaaaaaaaaaa");
		
		System.out.println(bean1.getValue()+"\t"+bean2.getValue());
	}
}
