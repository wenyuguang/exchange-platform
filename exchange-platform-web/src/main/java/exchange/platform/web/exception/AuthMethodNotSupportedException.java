package exchange.platform.web.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * 
 * Description:不支持的方法验证 / GET != POST
 * <p>Company: xinya </p>
 * <p>Date:2018年7月6日 上午11:18:12</p>
 * @author Tony
 * @version 1.0
 *
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
    private static final long serialVersionUID = 3705043083010304496L;

    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
