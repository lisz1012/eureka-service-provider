package com.lisz.controller;

import com.lisz.service.HealthIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@Autowired
	private HealthIndicatorService healthIndicatorService;

	@Value("${server.port}")
	private int port;

	@GetMapping("/getHi")
	public String getHi() {
		String selfIntro = "Hi from " + ':' + port;
		System.out.println(selfIntro);
		return selfIntro;
	}

	@GetMapping("/flipHealth")
	public String flipHealth(@RequestParam Boolean status) {
		healthIndicatorService.setUp(status);
		healthIndicatorService.health();
		return "success";
	}
}
