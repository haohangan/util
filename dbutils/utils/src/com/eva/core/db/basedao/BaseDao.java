package com.eva.core.db.basedao;

import java.io.Serializable;

import com.eva.core.db.entity.BasePojo;

public abstract class BaseDao<T extends BasePojo> {
	public abstract void insert(T t);

	public abstract void delete(Serializable pk);

	public abstract void update(T t);

	public abstract void queryAll();
}
