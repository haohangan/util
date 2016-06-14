package com.eva.pool;

/**
 * 池化对象示例
 * 
 * @author root
 *
 */
public class DataObject {
	private final String id;

	public DataObject(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "DataObject [id=" + id + "]";
	}

	
	public static void main(String[] args) {
		
	}
}
