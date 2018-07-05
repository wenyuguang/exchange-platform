package exchange.platform.gateway.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import exchange.platform.gateway.domain.ServiceInfo;

@Mapper
public interface GatewayMapper {

	public List<ServiceInfo> getServiceInfo();

	public ServiceInfo getServiceInfoByServiceEnName(String serviceEnName);
}
