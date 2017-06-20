package simple.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

public class Demo3 {
	
	static class Rtn{
		public Rtn(){
			System.out.println("init rtn");
		}
	}

	public static void main(String[] args) {
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(Rtn.class);
		hancer.setCallback(new LazyLoader(){

			@Override
			public Object loadObject() throws Exception {
				System.out.println("111111");
				return new Rtn();
			}
			
		});
//		hancer.setCallback(new Dispatcher(){
//
//			@Override
//			public Object loadObject() throws Exception {
//				System.out.println("-----22222222");
//				return new Rtn();
//			}
//			
//		});
		Object proxy = hancer.create();
		Rtn app = (Rtn) proxy;
System.out.println(app);
System.out.println(app);
System.out.println(app);
System.out.println(app);
//		 System.out.println(app.getDouble());
	}
}
