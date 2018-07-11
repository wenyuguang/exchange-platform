package exchange.platform.authentication.util;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月11日 上午11:58:08</p>
 * @author Tony
 * @version 1.0
 *
 */
public class UriUtil {

	public static final String TOKEN_HEADER_PARAM = "Authorization";
	public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/auth/**";
	public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/refresh_token";
}
