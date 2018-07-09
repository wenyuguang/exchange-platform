package exchange.platform.authentication.security.auth.token.verifier;

/**
 * 
 * Description:Token验证
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:38:19</p>
 * @author Tony
 * @version 1.0
 *
 */
public interface TokenVerifier {
    boolean verify(String jti);
}
