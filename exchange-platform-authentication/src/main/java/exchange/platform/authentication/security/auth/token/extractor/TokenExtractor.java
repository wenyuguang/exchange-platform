package exchange.platform.authentication.security.auth.token.extractor;

/**
 * 实现这个接口应该返回原Base-64编码
 * 表示Token
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:37:54</p>
 * @author Tony
 * @version 1.0
 *
 */
public interface TokenExtractor {
    String extract(String payload);
}
