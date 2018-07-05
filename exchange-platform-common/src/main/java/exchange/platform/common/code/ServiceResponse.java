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
	public ServiceResponse(long timestamp, int status, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}
	@Override
	public String toString() {
		return "{timestamp=" + timestamp + ", status=" + status + ", message=" + message + "}";
	}
    
}
