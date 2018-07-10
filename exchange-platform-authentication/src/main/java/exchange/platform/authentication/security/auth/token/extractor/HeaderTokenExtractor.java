package exchange.platform.authentication.security.auth.token.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import exchange.platform.common.auth.AuthUtil;

/**
 * 一个实现 {@link TokenExtractor} 接口 获取Token
 * 授权：Bearer scheme
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:37:44</p>
 * @author Tony
 * @version 1.0
 *
 */
@Component
public class HeaderTokenExtractor implements TokenExtractor {

    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        if (header.length() < AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }
        return header.replace(AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX, "");
    }
}
