package com.eva.rpcserver.base.data.method;

import java.io.Serializable;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年12月2日 上午10:27:08 类说明
 */
public class RpcParam implements Serializable{
	private static final long serialVersionUID = 1L;
	private Class<?> paramType;
	private Object paramObj;

	public Class<?> getParamType() {
		return paramType;
	}

	public void setParamType(Class<?> paramType) {
		this.paramType = paramType;
	}

	public Object getParamObj() {
		return paramObj;
	}

	public void setParamObj(Object paramObj) {
		this.paramObj = paramObj;
	}

}
