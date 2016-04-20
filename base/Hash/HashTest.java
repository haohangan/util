package c.hahs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月20日 上午9:59:49 类说明
 */
public class HashTest {
	static int i = 0;

	@Override
	public int hashCode() {
		// return super.hashCode();
		return i++;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
		// return false;
	}

	public static void main(String[] args) {
		Map<HashTest, String> h = new HashMap<HashTest, String>();
		HashTest ht = new HashTest();
		h.put(ht, "1111");
		h.put(ht, "222");
		print(h);
		Set<HashTest> s = new HashSet<HashTest>();
		s.add(ht);
		s.add(ht);
		print(s);
	}

	public static void print(Set<HashTest> set) {
		Iterator<HashTest> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public static void print(Map<HashTest, String> map) {
		Set<Map.Entry<HashTest, String>> set = map.entrySet();
		Iterator<Map.Entry<HashTest, String>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<HashTest, String> m = it.next();
			System.out.println(m.getKey() + "\t" + m.getValue());
		}
	}
}
