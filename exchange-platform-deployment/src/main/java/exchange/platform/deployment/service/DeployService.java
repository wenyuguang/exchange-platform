package exchange.platform.deployment.service;

import exchange.platform.common.code.InternalServiceResponse;

public interface DeployService {

	public InternalServiceResponse start(String serviceName);
	
	public InternalServiceResponse reStart(String serviceName);

	public InternalServiceResponse stop(String serviceName);

	public InternalServiceResponse deploy(String serviceName);

	public InternalServiceResponse check(String serviceName);

}
