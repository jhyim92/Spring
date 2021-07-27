package com.oracle.oBootMybatis03.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis03.dao.MemberJpaRepository;
import com.oracle.oBootMybatis03.domain.Member;

@Transactional
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;

	public MemberJpaService(MemberJpaRepository memberJpaRepository) {
		this.memberJpaRepository = memberJpaRepository;
	}

//	회원가입
	public Long join(Member member) {
		System.out.println("MemberJpaService Long join start...3번");
		System.out.println("MemberJpaService Long join member.getId()->" + member.getId());
		memberJpaRepository.save(member);
		return member.getId();
	}

//	전체회원 조회
	public List<Member> getListAllMember() {
		System.out.println("MemberJpaService List<Member> getListAllMember start...");
		List<Member> listMember = memberJpaRepository.findAll();
		System.out.println("MemberJpaService List<Member> getListAllMember listMember.size()->" + listMember.size());
		return listMember;
	}

//	회원상세조회
	public Optional<Member> findById(Long memberId) {
		System.out.println("MemberJpaService Optional<Member> findById start...");
		Optional<Member> member = memberJpaRepository.findById(memberId);
		return member;
	}
	
//	회원수정
	public void memberUpdate(Member member) {
		System.out.println("MemberJpaService void memberUpdate start...");
		System.out.println("MemberJpaService void memberUpdate member.getNameF()->"+member.getName());
		System.out.println("MemberJpaService void memberUpdate member.getId()->"+member.getId());
		memberJpaRepository.updateByMember(member);
		return;
	}

}
