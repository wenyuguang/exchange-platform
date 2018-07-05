package exchange.platform.common.auth;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午11:03:37</p>
 * @author Tony
 * @version 1.0
 *
 */
public class AuthUtil {

	/**
	 * 鉴权头部key信息
	 */
	public static final String AUTHORIZATION = "Authorization";
	/**
	 * JWT鉴权头部value前缀
	 */
	public static final String AUTHORIZATION_JWT_HEADER_PREFIX = "Bearer ";
	/**
	 * token申请登录头部前缀
	 */
	public static final String AUTHORIZATION_BASIC_HEADER_PREFIX = "Basic ";
	/**
	 * 私钥
	 */
	public static final String PRIVATESECRET = "PrivateSecret";
}
