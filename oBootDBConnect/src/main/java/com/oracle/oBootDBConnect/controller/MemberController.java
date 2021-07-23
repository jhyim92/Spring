package com.oracle.oBootDBConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootDBConnect.domain.Member;
import com.oracle.oBootDBConnect.service.MemberService;

@Controller
public class MemberController {
//	MemberService memberService = new MemberService();
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/new")
	public String createForm() {
		System.out.println("MemberController get/members/new start...");
		return "members/createMemberForm";
	}

	@PostMapping("/members/new")
	public String create(Member member) {
		System.out.println("MemberController post/members/new start...");
		memberService.join(member);
		return "redirect:/";
	}

	@GetMapping("/members")
	public String list(Model model) {
		List<Member> memberList = memberService.findMembers();
		model.addAttribute("members", memberList);
		return "members/memberList";
	}

}
