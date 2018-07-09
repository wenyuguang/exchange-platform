package exchange.platform.authentication.security.common;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * 
 * Description:客户端交互的错误模型
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:39:55</p>
 * @author Tony
 * @version 1.0
 *
 */
public class ErrorResponse {

	// HTTP 相应状态码
	private final HttpStatus status;

	// 错误信息
	private final String message;

	// 错误码
	private final ErrorCode errorCode;

	private final Date timestamp;

	protected ErrorResponse(final String message, final ErrorCode errorCode, HttpStatus status) {
		this.message = message;
		this.errorCode = errorCode;
		this.status = status;
		this.timestamp = new Date();
	}

	public static ErrorResponse of(final String message, final ErrorCode errorCode, HttpStatus status) {
		return new ErrorResponse(message, errorCode, status);
	}

	public Integer getStatus() {
		return status.value();
	}

	public String getMessage() {
		return message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
