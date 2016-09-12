package com.eva.rpc.nios.rpc.vo;

import java.io.Serializable;

public class KVPair implements Serializable{
	private static final long serialVersionUID = 1L;
	private Class<?> type;
	private Object value;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
