package exchange.platform.authentication.security.auth.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Description:客户端登录认证模型
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:37:29</p>
 * @author Tony
 * @version 1.0
 *
 */
public class LoginRequest {
    private String username;
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
