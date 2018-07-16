package exchange.platform.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import exchange.platform.common.code.ServiceResponse;
import exchange.platform.common.http.HttpStatus;

/**
 * 集群上服务进行流量监控
 * @author min
 *
 */
@Component
public class RateLimitZuulFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(RateLimitZuulFilter.class);
	
	private final RateLimiter rateLimiter = RateLimiter.create(9999.0);

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	@Override
	public boolean shouldFilter() {
		// 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。
		return true;

	}
	@Override
	public Object run() {
		try {
			RequestContext currentContext = RequestContext.getCurrentContext();
			HttpServletResponse response = currentContext.getResponse();
			if (!rateLimiter.tryAcquire()) {
				HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
				response.setContentType(MediaType.TEXT_PLAIN_VALUE);
				response.setStatus(httpStatus.value());
				response.getWriter()
					.append(
							new Gson()
								.toJson(
										new ServiceResponse(
												httpStatus.value(), 
												httpStatus.getReasonPhrase())));
				currentContext.setSendZuulResponse(false);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}
}