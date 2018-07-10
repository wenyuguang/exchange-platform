package exchange.platform.deployment.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import exchange.platform.common.code.InternalServiceResponse;

public interface DeployService {

	public InternalServiceResponse start(String serviceName);
	
	public InternalServiceResponse reStart(String serviceName);

	public InternalServiceResponse stop(String serviceName);

	public InternalServiceResponse deploy(MultipartFile file, HttpServletRequest request, String serviceName);

	public InternalServiceResponse check(String serviceName);

}
