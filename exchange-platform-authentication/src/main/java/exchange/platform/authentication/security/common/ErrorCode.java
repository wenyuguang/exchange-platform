package exchange.platform.authentication.security.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * Description:详细错误类型（枚举）
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:39:49</p>
 * @author Tony
 * @version 1.0
 *
 */
public enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);
    
    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
