package exchange.platform.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import exchange.platform.common.auth.AuthUtil;
import exchange.platform.common.code.ServiceResponse;
import exchange.platform.common.code.ServiceStatus;
import exchange.platform.common.domain.InvokeStatics;
import exchange.platform.common.http.HttpStatus;
import exchange.platform.common.net.NetUtils;
import exchange.platform.common.redis.RedisUtil;
import exchange.platform.gateway.domain.ServiceInfo;
import exchange.platform.gateway.service.AuthenticationService;

/**
 * 鉴权和路由管理
 * @author min
 *
 */
@Component
public class AuthenRouteFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AuthenRouteFilter.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		boolean invoke = true;
		long time = System.currentTimeMillis();
		String serviceEnName = null;
		try {
			ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
//	    	redisTemplate.setKeySerializer(new StringRedisSerializer());
//		    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
			RequestContext ctx = RequestContext.getCurrentContext();
			
			HttpServletRequest request = ctx.getRequest();
			String authorizationHead = request.getHeader(AuthUtil.AUTHORIZATION);
			String requestIp = NetUtils.getIpAddress(request);
			String requestUrl = request.getRequestURI();
			serviceEnName = requestUrl.substring(1, requestUrl.length()).substring(0, requestUrl.substring(1, requestUrl.length()).indexOf("/"));
			//服务路由校验
			ServiceInfo serviceInfo = null;
			//填写服务信息禁止直接写表，通过页面保存，同时向redis中保存一份
			if(redisTemplate.hasKey(String.format(RedisUtil.SERVICE_INFO_PREFIX, serviceEnName))) {
				serviceInfo = (ServiceInfo) valueOperations.get(String.format(RedisUtil.SERVICE_INFO_PREFIX, serviceEnName));
			}
			//网关只做token串校验，获取token串走另外获取接口
			if(!redisTemplate.hasKey(String.format(RedisUtil.SERVICE_INFO_PREFIX, serviceEnName))
					|| StringUtils.isEmpty(serviceInfo)
					|| serviceInfo.getEnableStatus().equalsIgnoreCase(ServiceStatus.DISABLE)
					|| !serviceInfo.getAccessIp().contains(requestIp)
					|| StringUtils.isEmpty(authorizationHead) 
					|| !authorizationHead.startsWith(AuthUtil.AUTHORIZATION_JWT_HEADER_PREFIX)
					|| !authenticationService.authenticateToken(authorizationHead)) {
				invoke = false;
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(401);
				ctx.setResponseBody(new Gson()
						.toJson(
								new ServiceResponse(
										System.currentTimeMillis(), 
										HttpStatus.UNAUTHORIZED
											.value(), 
										HttpStatus.UNAUTHORIZED
											.getReasonPhrase())));
				return null;
			}
			return null;
		} catch (Exception e) {
			invoke = false;
			logger.error(e.toString());
		}finally {
			redisTemplate.convertAndSend(RedisUtil.INVOKE_QUEUE, new InvokeStatics(serviceEnName, time, invoke));
		}
		return null;
	}

}