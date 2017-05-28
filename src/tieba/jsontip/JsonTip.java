package tieba.jsontip;

//json提示信息
public class JsonTip {

	protected Integer status=0;
	protected String message;
	 
	 public JsonTip(){}
	 
	public JsonTip(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
