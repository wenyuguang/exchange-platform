package exchange.platform.authentication.security.auth.token.verifier;

import org.springframework.stereotype.Component;

/**
 * 
 * Description:Token验证过滤器
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:38:27</p>
 * @author Tony
 * @version 1.0
 *
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
