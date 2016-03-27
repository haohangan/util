package ti.thread;

public class JsonVO {
	private boolean success;
	private String code;
	private Code obj;

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

	public Code getObj() {
		return obj;
	}

	public void setObj(Code obj) {
		this.obj = obj;
	}


	@Override
	public String toString() {
		return "JsonVO [success=" + success + ", code=" + code + ", obj=" + obj + "]";
	}


	static class Code {
		String id;
		int code;

		public Code() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return "Code [id=" + id + ", code=" + code + "]";
		}

	}
}
