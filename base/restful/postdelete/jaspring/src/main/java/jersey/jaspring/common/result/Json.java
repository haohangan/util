package jersey.jaspring.common.result;

import java.util.List;

public class Json {
	private String code;
	private boolean success;
	private String msg;
	private List<?> results;
	
	public Json() {
		super();
		success = true;
	}

	public Json(String code, boolean success, List<?> results) {
		super();
		this.code = code;
		this.success = success;
		this.results = results;
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

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
