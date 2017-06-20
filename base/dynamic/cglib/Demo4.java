package simple.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class Demo4 {

	static class Rtn {
		static int a = 0;
		private String str;

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
		// hancer.setCallback(new ProxyRefDispatcher(){
		//
		// @Override
		// public Object loadObject(Object proxy) throws Exception {
		// System.out.println("--------"+(String)proxy);
		// return new Rtn();
		// }
		//
		// });
		hancer.setCallback(NoOp.INSTANCE);
		Object proxy = hancer.create();
		Rtn app = (Rtn) proxy;
		System.out.println(app);
		app = (Rtn) proxy;
		System.out.println(app.getStr());
		app.setStr("pppp");
		System.out.println(app.getStr());
		// System.out.println(app.getDouble());
	}
}
