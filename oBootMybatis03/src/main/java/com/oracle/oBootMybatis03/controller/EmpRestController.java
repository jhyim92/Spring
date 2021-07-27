package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.SampleVo;
import com.oracle.oBootMybatis03.service.EmpService;

//@RestController = Controller + Response Body
@RestController
public class EmpRestController {

	@Autowired
	private EmpService es;

	@RequestMapping("/hello")
	public SampleVo hello() {
		System.out.println("EmpRestController SampleVo hello start...");
		SampleVo vo = new SampleVo();
		vo.setFirstName("hello");
		vo.setLastName("연");
		vo.setMno(123);
		return vo;
	}

	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController String helloText start...");
		String hello = "안녕";
		return hello;
	}
	
	@RequestMapping("/sample/sendVo2")
	public SampleVo sendVo2(int deptno) {
		System.out.println("EmpRestController SampleVo sendVo2 start...");
		SampleVo vo = new SampleVo();
		vo.setFirstName("임");
		vo.setLastName("준홍");
		vo.setMno(deptno);
		return vo;
	}

	@RequestMapping("/sendVo3")
	public List<Dept> sendVo3() {
		System.out.println("EmpRestController List<Dept> sendVo3 start...");
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}

}
