package exchange.platform.authentication.security.exceptions;

import org.springframework.security.core.AuthenticationException;

import exchange.platform.authentication.security.model.token.Token;


/**
 *  
 * Description:过期的Token
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:40:55</p>
 * @author Tony
 * @version 1.0
 *
 */
public class ExpiredTokenException extends AuthenticationException {
    private static final long serialVersionUID = -5959543783324224864L;
    
    private Token token;

    public ExpiredTokenException(String msg) {
        super(msg);
    }

    public ExpiredTokenException(Token token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
