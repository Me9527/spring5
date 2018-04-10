package org.example.framework.json;

public class JsonResult {
	private boolean success = false;
	private String message = "";
	private Object data = null;

	public JsonResult() {

	}

	public JsonResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public JsonResult(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	public JsonResult(boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
