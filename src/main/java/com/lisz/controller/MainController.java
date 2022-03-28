package com.lisz.controller;

import com.lisz.entity.Person;
import com.lisz.service.HealthIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

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

	@GetMapping("/getMap")
	public Map<String, String> getMap() {
		System.out.println("id=100");
		return Collections.singletonMap("id", "100");
	}

	@GetMapping("/flipHealth")
	public String flipHealth(@RequestParam Boolean status) {
		healthIndicatorService.setUp(status);
		healthIndicatorService.health();
		return "success";
	}

	@GetMapping("/getPerson")
	public Person getPerson(){
		return new Person(1, "zhangsan");
	}

	@GetMapping("/person/{name}")
	public Person getPerson2(@PathVariable String name){
		return new Person(1, name);
	}

	@GetMapping("/person")
	public Person getPerson3(@RequestParam int id, @RequestParam String name){
		return new Person(id, name);
	}


	@GetMapping("/person2")
	public Person getPerson4(@RequestParam Map<String, Object> map){
		return new Person(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}

	@PostMapping("/person3")
	public Person getPerson5(@RequestBody Person person){
		return new Person(person.getId() + 1, person.getName() + "---");
	}

	@PostMapping("/postLocation")
	public URI postParam(@RequestBody Person person, HttpServletResponse response) throws Exception{
		final URI uri = new URI("http://www.baidu.com/s?wd=" + person.getName());
		// 不能只是返回URI，还要设置header
		response.addHeader("Location", uri.toString());
		return uri;
	}
}
