package exchange.platform.common.code;

import java.io.Serializable;

/**
 * 内部服务调用响应
 * @author min
 *
 */
public class InternalServiceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135974869149349199L;
	/**
	 * true 为正常。 false为错误
	 */
	private boolean code;
	/**
	 * 消息
	 */
	private String  message;
	/**
	 * 返回时间戳
	 */
	private long    timestamp;
	/**
	 * 
	 * @param code true 为正常。 false为错误
	 * @param message 消息
	 * @param timestamp 返回时间戳
	 */
	public InternalServiceResponse(boolean code, String message, long timestamp) {
		super();
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}
	/**
	 * 
	 * @param code true 为正常。 false为错误
	 * @param message 消息
	 */
	public InternalServiceResponse(boolean code, String message) {
		super();
		this.code = code;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}
	/**
	 * @return the code
	 */
	public boolean isCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(boolean code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
