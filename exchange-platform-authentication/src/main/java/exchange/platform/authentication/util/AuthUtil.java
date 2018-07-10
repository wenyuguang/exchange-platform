package exchange.platform.authentication.util;

public class AuthUtil {

	public static final String TOKEN_HEADER_PARAM = "Authorization";
	public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/auth/**";
//    private static final String MANAGE_TOKEN_BASED_AUTH_ENTRY_POINT = "/manage/**";
	public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/refresh_token";
}
