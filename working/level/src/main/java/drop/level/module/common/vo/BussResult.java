package drop.level.module.common.vo;

public class BussResult {
	private String code;
	private boolean success;
	private String msg;
	private String content;

	public static class Builder {
		private String code;
		private boolean success;
		private String msg;
		private String content;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public Builder success() {
			this.success = true;
			return this;
		}

		public Builder fail() {
			this.success = false;
			return this;
		}
		
		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public BussResult build() {
			BussResult result = new BussResult();
			result.setCode(this.code);
			result.setMsg(this.msg);
			result.setSuccess(this.success);
			result.setContent(this.content);
			return result;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
