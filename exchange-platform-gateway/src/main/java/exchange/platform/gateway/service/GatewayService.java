package exchange.platform.gateway.service;

import java.util.List;

import exchange.platform.gateway.domain.ServiceInfo;

public interface GatewayService {

	/**
	 * 
	 * @function 获取所有服务信息
	 * @author Tony
	 * @creaetime 2018年7月3日 上午11:29:44
	 * @return
	 */
	public List<ServiceInfo> getServiceInfo();
	/**
	 * 
	 * @function 根据服务名获取服务信息
	 * @author Tony
	 * @creaetime 2018年7月3日 上午11:29:56
	 * @param serviceEnName
	 * @return
	 */
	public ServiceInfo getServiceInfoByServiceEnName(String serviceEnName);
}
