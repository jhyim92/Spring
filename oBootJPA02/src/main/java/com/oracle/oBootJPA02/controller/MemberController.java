package com.oracle.oBootJPA02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJPA02.domain.Member;
import com.oracle.oBootJPA02.service.MemberService;

@Controller
public class MemberController {
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/new")
	public String createForm() {
		System.out.println("MemberController JPA GetMapping /member/new start...");
		return "members/createMemberForm";
	}

	@PostMapping("/members/save")
	public String create(Member member) {
		System.out.println("MemberController JPA PostMapping create start");
		System.out.println("member.getName()->" + member.getName());
		System.out.println("member.getTeamname()->" + member.getTeamname());
		memberService.join(member);
		return "redirect:/";
	}

	@GetMapping("/members")
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("memberList.get(0).getName()->" + memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeam().getName()->" + memberList.get(0).getTeam().getName());
		model.addAttribute("members", memberList);
		return "members/memberList";
	}

	@GetMapping("/memberModifyForm")
	public String memberModify(Long id, Model model) {
		System.out.println("MemberController memberModify id->" + id);
		Optional<Member> member = memberService.findByMember(id);
		Member member2 = new Member();
		member2.setId(member.get().getId());
		System.out.println("MemberController memberModifyForm2");
		member2.setName(member.get().getName());
		System.out.println("MemberController memberModifyForm3");
		member2.setTeam(member.get().getTeam());
		System.out.println("MemberController memberModifyForm4");

		System.out.println("member.get().getId()->" + member.get().getId());
		System.out.println("member.get().getName()->" + member.get().getName());
		System.out.println("member.get().getTeam().getName()->" + member.get().getTeam().getName());

		model.addAttribute("member", member2);
		return "members/memberModify";
	}

	@GetMapping("/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberController memberUpdate member.getId()->" + member.getId());
		System.out.println("MemberController memberUpdate member.getName()->" + member.getName());
		System.out.println("MemberController memberUpdate member.getTeamname()->" + member.getTeamname());
		memberService.memberUpdate(member);
		return "redirect:/members";
	}

	@PostMapping("/members/search")
	public String search(Member member, Model model) {
		System.out.println("MemberController search member.getName()->" + member.getName());
		List<Member> memberList = memberService.getListSearchMember(member.getName());
		System.out.println("MemberController search memberList.size()->" + memberList.size());
		model.addAttribute("members", memberList);
		return "members/memberList";
	}

}
