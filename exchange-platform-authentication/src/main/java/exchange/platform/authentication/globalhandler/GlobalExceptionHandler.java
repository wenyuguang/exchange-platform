package exchange.platform.authentication.globalhandler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import exchange.platform.common.code.ServiceResponse;
import exchange.platform.common.http.HttpStatus;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月11日 上午11:07:06</p>
 * @author Tony
 * @version 1.0
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 
	 * @function 
	 * @author Tony
	 * @creaetime 2018年7月11日 上午11:06:51
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler
	@ResponseBody
	public ServiceResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			if(logger.isDebugEnabled())
				logger.debug(req.getRequestURI()+"not found,please check url");
			return new ServiceResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
		}
		return null;
	}
}