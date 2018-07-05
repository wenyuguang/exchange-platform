package exchange.platform.deployment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import exchange.platform.deployment.service.DeployService;

@RestController
public class DeployController {
	
	@Autowired
	private DeployService deployservice;
	
	@RequestMapping("start")
	public String start (String serviceName) {
		return new Gson().toJson(deployservice.start(serviceName));
	}
	
	@RequestMapping("restart")
	public String reStart (String serviceName) {
		return new Gson().toJson(deployservice.reStart(serviceName));
	}
	
	@RequestMapping("stop")
	public String stop (String serviceName) {
		return new Gson().toJson(deployservice.stop(serviceName));
	}
	
	@RequestMapping("deploy")
	public String deploy (String serviceName) {
		return new Gson().toJson(deployservice.deploy(serviceName));
	}
	
	@RequestMapping("check")
	public String check (String serviceName) {
		return new Gson().toJson(deployservice.check(serviceName));
	}
}
