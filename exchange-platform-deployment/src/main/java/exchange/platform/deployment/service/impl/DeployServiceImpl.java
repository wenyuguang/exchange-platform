package exchange.platform.deployment.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import exchange.platform.common.code.InternalServiceResponse;
import exchange.platform.deployment.service.DeployService;

@Service
public class DeployServiceImpl implements DeployService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	private static final String ROOT_DIR_NAME = "deploy";
	private static final String DELIMITER     = "//";
	
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
	public InternalServiceResponse check(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalServiceResponse deploy(MultipartFile file, HttpServletRequest request, String serviceName) {
		String contentType = file.getContentType();
	    String fileName = file.getOriginalFilename();
	    /*System.out.println("fileName-->" + fileName);
	    System.out.println("getContentType-->" + contentType);*/
	    String filePath = request.getSession().getServletContext().getRealPath(ROOT_DIR_NAME+DELIMITER+serviceName+DELIMITER);
	    try {
			Files.createDirectories(Paths.get(filePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    try {
	    	Files.copy(file.getInputStream(), Paths.get(filePath+fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//	    File targetFile = new File(filePath);  
//	    if(!targetFile.exists()){    
//	        targetFile.mkdirs();    
//	    }
//	    FileOutputStream out = null;
//		try {
//			out = new FileOutputStream(filePath+fileName);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	    try {
//			out.write(file.getBytes());
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return new InternalServiceResponse(true, "SUCCESS");
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
