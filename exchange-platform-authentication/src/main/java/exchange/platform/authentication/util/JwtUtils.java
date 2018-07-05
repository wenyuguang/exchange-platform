package exchange.platform.authentication.util;

import org.springframework.util.StringUtils;

import exchange.platform.common.auth.AuthUtil;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月2日 上午10:56:27</p>
 * @author Tony
 * @version 1.0
 *
 */
public class JwtUtils {
    

    /**
     * 
     * @function 
     * @author Tony
     * @creaetime 2018年7月2日 上午10:56:43
     * @param authorizationHeader
     * @return
     */
    public static String getRawToken(String authorizationHeader) {
        return authorizationHeader.substring(AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX.length());
    }

    public static String getTokenHeader(String rawToken) {
        return AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX + rawToken;
    }

    public static boolean validate(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX);
    }

    public static String getAuthorizationHeaderPrefix() {
        return AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX;
    }
}
