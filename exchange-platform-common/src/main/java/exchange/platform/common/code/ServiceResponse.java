package exchange.platform.common.code;

public class ServiceResponse {

	private long   timestamp;
	private int    status;
    private String message;
    /**
     * 
     * @param timestamp 当前时间戳
     * @param status    http状态
     * @param message   消息内容
     */
	public ServiceResponse(int status, String message) {
		super();
		this.timestamp = System.currentTimeMillis();
		this.status = status;
		this.message = message;
	}
	
	
	/**
	 * get timestamp value
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}


	/**
	 * get status value
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * get message value
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	@Override
	public String toString() {
		return "{timestamp=" + timestamp + ", status=" + status + ", message=" + message + "}";
	}
    
}
