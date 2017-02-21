package com.gamma.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guushamm on 21-2-17.
 */
@Controller
public class HelloWorldController {

	@RequestMapping("/")
	public String index(@RequestParam(value="name", defaultValue = "World") String name){
		return String.format("Hello %s", name);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HelloWorldController.class, args);
	}
}
