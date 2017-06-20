package simple.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.NoOp;

public class Demo5 {

	static class Rtn {
		static int a = 0;
		private String str;
		
		private int getInt(){
			return a;
		}

		@Override
		public String toString() {
			return "Rtn [str=" + str + "]";
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public Rtn() {
			System.out.println("init rtn");
			str = (++a)+ "";
		}
	}

	public static void main(String[] args) {
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(Rtn.class);
		
		CallbackHelper helper  = new CallbackHelper(Rtn.class,new Class[0]) {
			
			@Override
			protected Object getCallback(Method method) {
				if(method.getDeclaringClass() != Object.class && method.getReturnType()==String.class){
					return new FixedValue() {
						
						@Override
						public Object loadObject() throws Exception {
							return "hello cglib";
						}
					};
				}else{
					return NoOp.INSTANCE;// A singleton provided by NoOp.
				}
			}
		};
		
		hancer.setCallbackFilter(helper);
		hancer.setCallbacks(helper.getCallbacks());
		
		test(hancer);
		System.out.println("============");
		test(hancer);
	}

	private static void test(Enhancer hancer) {
		Object proxy = hancer.create();
		Rtn app = (Rtn) proxy;
		System.out.println(app);
		app = (Rtn) proxy;
		System.out.println(app.getStr());
		app.setStr("pppp");
		System.out.println(app.getStr());
		
		System.out.println(app.getInt());
	}
}
