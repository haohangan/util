package com.eva.rpc.io.common;

import java.util.Arrays;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * 请求类
 * 
 * @author root
 *
 */
public class RPCRequestVO {
	static final Schema<RPCRequestVO> schema = RuntimeSchema.createFrom(RPCRequestVO.class);

	private String num;
	private Class<?> protocal;
	private String method;
	private Class<?>[] params;
	private Object[] values;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Class<?> getProtocal() {
		return protocal;
	}

	public void setProtocal(Class<?> protocal) {
		this.protocal = protocal;
	}

	public Class<?>[] getParams() {
		return params;
	}

	public void setParams(Class<?>[] params) {
		this.params = params;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	/**
	 * 编码
	 * 
	 * @return
	 */
	public byte[] serialize() {
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		return ProtostuffIOUtil.toByteArray(this, schema, buffer);
	}

	/**
	 * 解码
	 * 
	 * @param bytes
	 * @return
	 */
	public RPCRequestVO desrialize(byte[] bytes) {
		ProtostuffIOUtil.mergeFrom(bytes, this, schema);
		return this;
	}

	@Override
	public String toString() {
		return "RPCRequestVO [num=" + num + ", protocal=" + protocal + ", method=" + method + ", params="
				+ Arrays.toString(params) + ", values=" + Arrays.toString(values) + "]";
	}

}
