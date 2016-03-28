package com.eva.rpcserver.base.data.intf;

import com.eva.rpcserver.base.data.method.RpcMethod;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年12月1日 下午4:36:47 类说明
 */
public interface ExchangeData {
	public Class<?> getInvocationClass() throws Exception;

	public RpcMethod getMethod() throws Exception;

	public Object[] getResult();
}
