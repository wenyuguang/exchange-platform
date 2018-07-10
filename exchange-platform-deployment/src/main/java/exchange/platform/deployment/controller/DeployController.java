package exchange.platform.deployment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import exchange.platform.deployment.service.DeployService;

@RestController
public class DeployController {
	
	@Autowired
	private DeployService deployservice;
	
	@GetMapping("start")
	public String start (@RequestParam("serviceName")String serviceName) {
		return new Gson().toJson(deployservice.start(serviceName));
	}
	
	@GetMapping("restart")
	public String reStart (@RequestParam("serviceName")String serviceName) {
		return new Gson().toJson(deployservice.reStart(serviceName));
	}
	
	@GetMapping("stop")
	public String stop (@RequestParam("serviceName")String serviceName) {
		return new Gson().toJson(deployservice.stop(serviceName));
	}
	
	@PostMapping("deploy")
	public String deploy (@RequestParam("file") MultipartFile file,
			@RequestParam("serviceName")String serviceName,
            HttpServletRequest request) {
		return new Gson().toJson(deployservice.deploy(file, request, serviceName));
	}
	
	@GetMapping("check")
	public String check (@RequestParam("serviceName")String serviceName) {
		return new Gson().toJson(deployservice.check(serviceName));
	}
}
