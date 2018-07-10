package exchange.platform.authentication.security.model.token;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;

import exchange.platform.authentication.security.model.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * 
 * Description:Token刷新工具类
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:41:36</p>
 * @author Tony
 * @version 1.0
 *
 */
public class RefreshToken implements Token {
    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    /**
     * Creates and validates Refresh token
     *
     * @param token
     * @param signingKey
     * @return
     * @throws BadCredentialsException
     */
    public static Optional<RefreshToken> create(RawAccessToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);
        @SuppressWarnings("unchecked")
        List<String> scopes = claims.getBody().get("scopes", List.class);
        if (scopes == null 
        		|| scopes.isEmpty()
                || scopes.stream().noneMatch(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope))
                ) {
            return Optional.empty();
        }
        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }
}
