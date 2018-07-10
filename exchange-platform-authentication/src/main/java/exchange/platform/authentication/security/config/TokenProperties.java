package exchange.platform.authentication.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:40:28</p>
 * @author Tony
 * @version 1.0
 *
 */
@Configuration
@ConfigurationProperties(prefix = "exchange.platform.token")
public class TokenProperties {
    /**
     * {@link com.battcn.security.model.token.Token} token的过期时间
     */
    private Integer expirationTime;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 使用的签名KEY {@link com.battcn.security.model.token.Token}.
     */
    private String signingKey;

    /**
     * {@link com.battcn.security.model.token.Token} 刷新过期时间
     */
    private Integer refreshExpTime;

    public Integer getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public Integer getRefreshExpTime() {
        return refreshExpTime;
    }

    public void setRefreshExpTime(Integer refreshExpTime) {
        this.refreshExpTime = refreshExpTime;
    }

}
