package com.eva.rpc.nios.rpc.vo;

import java.io.Serializable;
import java.util.Arrays;

public class RequestVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Class<?> type;
	private String methodName;
	private KVPair[] kvs;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public KVPair[] getKvs() {
		return kvs;
	}

	public void setKvs(KVPair[] kvs) {
		this.kvs = kvs;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public void initKVS(Class<?> type,Object value){
		KVPair kv = new KVPair();
		kv.setType(type);
		kv.setValue(value);
		kvs = new KVPair[]{kv};
	}

	public Class<?>[] parameterTypes() {
		Class<?>[] clazzs = new Class<?>[kvs.length];
		int i = 0;
		for (KVPair kv : kvs) {
			clazzs[i++] = kv.getType();
		}
		return clazzs;
	}

	public Object[] parameterValues() {
		Object[] objs = new Object[kvs.length];
		int i = 0;
		for (KVPair kv : kvs) {
			objs[i++] = kv.getValue();
		}
		return objs;
	}

	@Override
	public String toString() {
		return "RequestVO [type=" + type + ", methodName=" + methodName + ", kvs=" + Arrays.toString(kvs) + "]";
	}

}
