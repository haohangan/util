package curator_y.common_configuration.buss.controller.responsevo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class Json implements Serializable{
	private static final long serialVersionUID = -4853244179938087768L;
	
	private String id;
	private String msg;
	private boolean success;
	
	public static class Jsonbuilder{
		private String id;
		private String msg;
		private boolean success;
		public Jsonbuilder() {
			super();
		}
		
		public Jsonbuilder id(String id){
			this.id = id;
			return this;
		}
		
		public Jsonbuilder msg(String msg){
			this.msg = msg;
			return this;
		}
		
		public Jsonbuilder SUCCESS(){
			this.success = true;
			return this;
		}
		
		public Jsonbuilder FAIL(){
			this.success = false;
			return this;
		}
		
		public Json build(){
			Json json = new Json();
			if(StringUtils.isNotBlank(this.id)){
				json.setId(this.id);
			}
			if(StringUtils.isNotBlank(this.msg)){
				json.setMsg(this.msg);
			}
			json.setSuccess(success);
			return json;
		}
	}

	public Json(String id, String msg, boolean success) {
		super();
		this.id = id;
		this.msg = msg;
		this.success = success;
	}

	public Json() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
