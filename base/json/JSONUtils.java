package com.grgbanking.api.action.utils;

import com.grgbanking.api.action.vo.ReqBaseVO;

import flexjson.JSONSerializer;

public class JSONUtils {
	private JSONUtils() {
	}

	public static String toJsonString(ReqBaseVO vo) {
		JSONSerializer serializer = new JSONSerializer();
		return serializer.exclude("*.class").deepSerialize(vo);
	}

	
}
