package com.oracle.oBootMybatis03.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis03.domain.Member;
import com.oracle.oBootMybatis03.service.MemberJpaService;

@Controller
public class MemberJpaController {
	private final MemberJpaService memberJpaService;

	@Autowired
	public MemberJpaController(MemberJpaService memberJpaService) {
		this.memberJpaService = memberJpaService;
	}

	@GetMapping("/memberJpa/new")
	public String createForm() {
		System.out.println("MemberJpaController String createForm start...1번");
		return "memberJpa/createMemberForm";
	}

	@PostMapping("/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberJpaController String create start... 2번");
		System.out.println("MemberJpaController String create member.getId()->" + member.getId());
		System.out.println("MemberJpaController String create member.getName()->" + member.getName());
		memberJpaService.join(member);

		return "memberJpa/createMemberForm";
	}

	@GetMapping("/members")
	public String listMember(Model model) {
		System.out.println("MemberJpaController String listMember start...");
		List<Member> memberList = memberJpaService.getListAllMember();
		System.out.println("MemberJpaController String listMember memberList.size()->" + memberList.size());
		model.addAttribute("members", memberList);
		return "memberJpa/memberList";
	}

	@GetMapping("/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Long id, Model model) {
		System.out.println("MemberJpaController String memberUpdateForm start...");
		Member member = null;
		String rtnJsp = "";
		System.out.println("MemberJpaController memberUpdateForm id->" + id);
		Optional<Member> maybeMember = memberJpaService.findById(id);
		if (maybeMember.isPresent()) {
			System.out.println("MemberJpaController memberUpdateForm maybeMember is not null");
			member = maybeMember.get();
			model.addAttribute("member", member);
			rtnJsp = "memberJpa/memberModify";
		} else {
			System.out.println("MemberJpaController memberUpdateForm maybeMember is null");
			model.addAttribute("message", "member가 존재하지 않으니, 입력부터 수정해 주세요");
			rtnJsp = "forward:/members";
		}
		return rtnJsp;
	}
	
	@GetMapping("/memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberJpaController String memberUpdate start...");
		System.out.println("MemberJpaController String memberUpdate member.getId()->"+member.getId());
		System.out.println("MemberJpaController String memberUpdate member.getNameF()->"+member.getName());
		memberJpaService.memberUpdate(member);
		return "redirect:/members";
	}

}
