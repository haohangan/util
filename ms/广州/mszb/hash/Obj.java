package testhashcode;

import java.util.HashSet;
import java.util.Set;
/*自己做得面试题*/
public class Obj {
    String name;
	static int i = 0;
	
	public Obj(String name) {
		super();
		this.name = name;
	}

	@Override
	public int hashCode() {
		return i++;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	/**
	 * 什么时候会调用hashcode这个方法，判断输出的结果
	 * @param args
	 */
	public static void main(String[] args) {
		Obj o1 = new Obj("A");
		Obj o2 = new Obj("B");
		System.out.println(o1.hashCode()==o2.hashCode());
		System.out.println(o1==o2);
		System.out.println(o1.equals(o2));
		Set<Obj> set = new HashSet<>();
		set.add(o1);
		set.add(o2);
		set.add(o1);
		set.add(o2);
		
		set.forEach(e->{
			System.out.println(e+"\t"+e.name);
		});
	}
}
