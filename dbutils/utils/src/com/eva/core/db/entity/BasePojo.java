package com.eva.core.db.entity;

import java.io.Serializable;

public abstract class BasePojo implements Serializable{

	private static final long serialVersionUID = -1652673919925424151L;
    
	public abstract Class<?> getPojoType();
	
}
