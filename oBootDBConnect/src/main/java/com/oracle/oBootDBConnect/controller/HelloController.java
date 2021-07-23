package com.oracle.oBootDBConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootDBConnect.domain.Hello3;

@Controller
public class HelloController {
	@RequestMapping("Hello")
	public String hello(Model model) {
		System.out.println("HelloController Hello start...");
		model.addAttribute("data", "Server Parameter");
		return "hello";
	}

	@GetMapping("HelloGet")
	public String helloGet(Model model) {
		System.out.println("HelloController helloGet start...");
		model.addAttribute("data", "Get Parameter");
		return "helloGet";
	}

	// @ResponseBody를 사용하고, 객체를 반환하면 객체가 JSON으로 변환
	@GetMapping("HelloString")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		System.out.println("HelloController helloString start...");
		return "hello ResponseBody -->" + name;
	}
	
	@GetMapping("HelloApi")
	@ResponseBody
	public Hello3 helloApi(@RequestParam("name") String name) {
		Hello3 hello3 = new Hello3();
		hello3.setName(name);
		
		System.out.println("HelloController HelloApi start...");
		return hello3;
	}
	
	@GetMapping("/")
	public String home() {
		System.out.println("HelloController home start...");
		return "home";
	}

}
