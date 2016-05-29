package com.eva.model.user.authcache;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

public class AuthExame {
	static Map<String, Set<String>> authCache = null;// 共享的数据

	static {
		authCache = Collections.unmodifiableMap(AuthCache.getAuthCache());
	}

	public static Boolean exameOperation(final String url, final Set<String> roleName) {
		Boolean flag = false;
		if (!authCache.containsKey(url)) {
			return flag;
		}
//		if (!authCache.get(url).contains(roleName)) {
//			return flag;
//		}
		if(roleName.isEmpty()){
			return flag;
		}else{
			Set<String> cacheRoles = authCache.get(url);
			for(String t : roleName){
				if(cacheRoles.contains(t)){
					return true;
				}
			}
			return flag;
		}
	}
	
	public static Boolean exameOperation(final String url, final String roleName) {
		final Boolean flag = false;
//		printCache();
		if (!authCache.containsKey(url)) {
			return flag;
		}
		if(!StringUtils.hasLength(roleName)){
			return flag;
		}else{
			String[] arr = roleName.split(",");
			if(arr==null || arr.length==0){
				return flag;
			}
			Set<String> cacheRoles = authCache.get(url);
			for(String t : arr){
				if(cacheRoles.contains(t)){
					return true;
				}
			}
			return flag;
		}
	}
	
	static void printCache(){
		authCache.forEach((k,v)->System.out.println(k+"\t"+v));
	}
}
