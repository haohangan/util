package com.eva.model.user.authcache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.eva.entity.Operation;
import com.eva.entity.Role;

public class AuthCache {
	private static Map<String, Set<String>> authCache;

	private AuthCache() {
	}

	public static Map<String, Set<String>> getAuthCache() {
		return authCache;
	}

	public static void init(Iterable<Operation> list) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		list.forEach((t) -> {
			String key = t.getOpUrl();
			Set<Role> set = t.getRoles();
			Set<String> rolename = new HashSet<String>();
			set.forEach((r) -> rolename.add(r.getRoleName()));
			map.put(key, rolename);
		});
//		authCache = Collections.unmodifiableMap(map);
		authCache = map;
	}

//	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("g", "www.google.com");
//		map.put("b", "www.bing.com");
//
//		Map<String, String> unmap = Collections.unmodifiableMap(map);// 不要想着修改他
//		unmap.forEach((k, v) -> System.out.println(k + "\t" + v));
		// unmap.put("y", "www.youtube.com");//throw new
		// UnsupportedOperationException();
		// unmap.remove("g");//throw new UnsupportedOperationException();
		// System.out.println("====================");
		// unmap.forEach((k,v)->System.out.println(k+"\t"+v));
//	}

}
