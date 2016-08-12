package com.eva.rpc.io.common;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * 结果类
 * 
 * @author root
 *
 */
public class ResultVO {
	static final Schema<ResultVO> schema = RuntimeSchema.createFrom(ResultVO.class);

	private String code;
	private boolean success;
	private String msg;
	private Object result;

	public static class Builder {
		private String code;
		private boolean success;
		private String msg;
		private Object result;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder success() {
			this.success = Boolean.TRUE;
			return this;
		}

		public Builder faliure() {
			this.success = Boolean.FALSE;
			return this;
		}

		public Builder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public Builder result(Object result) {
			this.result = result;
			return this;
		}

		public ResultVO build() {
			ResultVO rvo = new ResultVO();
			rvo.setCode(this.code);
			rvo.setSuccess(this.success);
			rvo.setMsg(this.msg);
			rvo.setResult(this.result);
			return rvo;
		}
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * 编码
	 * 
	 * @return
	 */
	public byte[] serialize() {
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);// LinkedBuffer.DEFAULT_BUFFER_SIZE
		return ProtostuffIOUtil.toByteArray(this, schema, buffer);
	}

	/**
	 * 解码
	 * 
	 * @param bytes
	 * @return
	 */
	public ResultVO deserialize(byte[] bytes) {
		ProtostuffIOUtil.mergeFrom(bytes, this, schema);
		return this;
	}

	@Override
	public String toString() {
		return "ResultVO [code=" + code + ", success=" + success + ", msg=" + msg + ", result=" + result + "]";
	}

}
