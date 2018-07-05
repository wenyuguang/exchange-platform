package exchange.platform.gateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exchange.platform.gateway.domain.ServiceInfo;
import exchange.platform.gateway.mapper.GatewayMapper;
import exchange.platform.gateway.service.GatewayService;

@Service
public class GatewayServiceImpl implements GatewayService {

	@Autowired
	private GatewayMapper gatewayMapper;
	
	@Override
	public List<ServiceInfo> getServiceInfo() {
		List<ServiceInfo> serviceInfoList = gatewayMapper.getServiceInfo();
		return serviceInfoList;
	}

	@Override
	public ServiceInfo getServiceInfoByServiceEnName(String serviceEnName) {
		ServiceInfo serviceInfo = gatewayMapper.getServiceInfoByServiceEnName(serviceEnName);
		return serviceInfo;
	}

}
