package com.oracle.mvc152;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@RequestMapping("/studentForm")
	public String studentForm() {
		logger.info("Welcome studentForm start...");

		return "createPage";
	}

	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
		String page = "createDonePage";
//	 	BindingResult -> 유효성 검증결과를 담아두는 객체
		StudentValidator validator = new StudentValidator();
		validator.validate(student, result);
		if (result.hasErrors()) {
			page = "createPage";
		}

		return page;
	}

}
