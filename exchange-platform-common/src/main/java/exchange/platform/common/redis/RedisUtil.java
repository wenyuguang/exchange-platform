package exchange.platform.common.redis;

public class RedisUtil {

	/**
	 * 缓存服务信息前缀
	 */
	public static final String SERVICE_INFO_PREFIX = "sip.%s";
	/**
	 * 调用统计前缀
	 */
	public static final String INVOKE_PREFIX       = "ip.%s";
	/**
	 * 统计调阅日志队列
	 */
	public static final String INVOKE_QUEUE        = "invoke.queue";
}
