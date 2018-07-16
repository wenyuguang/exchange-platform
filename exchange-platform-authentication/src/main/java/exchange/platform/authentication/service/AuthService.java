package exchange.platform.authentication.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

	public Object refreshToken(HttpServletRequest request);

	public boolean verify(String tokens, String serviceEnName);

}
