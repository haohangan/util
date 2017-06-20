package simple.cglib.keyfactory;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.core.KeyFactory;

public class TestKeyFactory {
   public static void main(String[] args) {
	   SampleKeyFactory factory = (SampleKeyFactory)KeyFactory.create(SampleKeyFactory.class);
	   Object key1 = factory.newInstance("foo", 42);
	   Object key2 = factory.newInstance("foo1", 41);
	   System.out.println(key1);
	   System.out.println(key2);
//	   Map<Object, String> map = new HashMap<Object, String>();
//	   map.put(key, "Hello cglib!");
//	   System.out.println(map.get(factory.newInstance("foo", 42)));
//	   System.out.println(map.get(factory.newInstance("foo", 42)));
//	   System.out.println(map.get(factory.newInstance("foo", 42)));
//	   System.out.println(map.get(factory.newInstance("foo", 42)));
}
}
