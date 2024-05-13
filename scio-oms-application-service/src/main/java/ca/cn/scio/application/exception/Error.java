package ca.cn.scio.application.exception;

public class Error {
	private ErrorCode errorCode;
	private String description;

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setError(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.description = errorCode.getDescription();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
