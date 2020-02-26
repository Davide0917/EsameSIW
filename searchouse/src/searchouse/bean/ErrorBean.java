package searchouse.bean;

public class ErrorBean implements Bean, java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3132932700386274426L;
	private String message;
	private String comeBack;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getComeBack() {
		return comeBack;
	}

	public void setComeBack(String comeBack) {
		this.comeBack = comeBack;
	}
	
}
