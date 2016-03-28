package com.eva.rpcserver.base.data.method;

import java.io.Serializable;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年12月2日 上午10:25:15 类说明
 */
public class RpcMethod implements Serializable {
	private static final long serialVersionUID = 1L;
	private RpcParam[] params;
	private String mathodName;

	public RpcParam[] getParams() {
		return params;
	}

	public void setParams(RpcParam[] params) {
		this.params = params;
	}

	public String getMathodName() {
		return mathodName;
	}

	public void setMathodName(String mathodName) {
		this.mathodName = mathodName;
	}

}
