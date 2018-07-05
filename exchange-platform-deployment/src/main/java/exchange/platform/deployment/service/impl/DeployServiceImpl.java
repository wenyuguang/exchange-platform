package exchange.platform.deployment.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import exchange.platform.common.code.InternalServiceResponse;
import exchange.platform.deployment.service.DeployService;

@Service
public class DeployServiceImpl implements DeployService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Override
	public InternalServiceResponse start(String serviceName) {
//		List<String> serviceList = discoveryClient.getServices();
		List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
		for(ServiceInstance serviceInstance:serviceInstanceList) {
			if(serviceInstance.getServiceId().equals(serviceName)) {
				int port = serviceInstance.getPort();
				boolean isUsing = false;
				try {
					isUsing = isPortUsing(port);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				if(isUsing) {
					return new InternalServiceResponse(true, "OK", System.currentTimeMillis());
				}
			}
		}
		// TODO Auto-generated method stub
		return new InternalServiceResponse(false, "ERROR", System.currentTimeMillis());
	}
	
	@Override
	public InternalServiceResponse reStart(String serviceName) {
//		List<String> serviceList = discoveryClient.getServices();
		List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
		for(ServiceInstance serviceInstance:serviceInstanceList) {
			if(serviceInstance.getServiceId().equals(serviceName)) {
				int port = serviceInstance.getPort();
				boolean isUsing = false;
				try {
					isUsing = isPortUsing(port);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				if(isUsing) {
					
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalServiceResponse stop(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalServiceResponse deploy(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalServiceResponse check(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 检查本地端口是否打开
	 * @param host
	 * @param port
	 * @return
	 * @throws UnknownHostException
	 */
	public boolean isPortUsing(int port) throws UnknownHostException{  
        boolean flag = false;  
        InetAddress Address = InetAddress.getByName("127.0.0.1");  
        try {  
            Socket socket = new Socket(Address,port);  //建立一个Socket连接
            socket.close();
            flag = true;
        } catch (IOException e) {  

        }  
        return flag;  
    }  
}
