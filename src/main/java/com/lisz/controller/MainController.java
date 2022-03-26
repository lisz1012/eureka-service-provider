package com.lisz.controller;

import com.lisz.service.HealthIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@Autowired
	private HealthIndicatorService healthIndicatorService;

	@GetMapping("/getHi")
	public String getHi() {
		System.out.println("Hi");
		return "Hi";
	}

	@GetMapping("/flipHealth")
	public String flipHealth(@RequestParam Boolean status) {
		healthIndicatorService.setUp(status);
		healthIndicatorService.health();
		return "success";
	}
}
