package exchange.platform.authentication.security.model.token;

import io.jsonwebtoken.Claims;

/**
 * 
 * Description:验证Token
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:41:22</p>
 * @author Tony
 * @version 1.0
 *
 */
public final class AccessToken implements Token {
    private final String rawToken;
    private Claims claims;

    protected AccessToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
