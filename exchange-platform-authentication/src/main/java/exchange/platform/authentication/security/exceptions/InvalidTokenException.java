package exchange.platform.authentication.security.exceptions;

/**
 * 
 * Description:无效的Token
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:41:10</p>
 * @author Tony
 * @version 1.0
 *
 */
public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = -294671188037098603L;

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException(String msg, Throwable t) {
        super(msg, t);
    }
}
