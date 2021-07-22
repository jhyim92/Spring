package com.oracle.oBootAPI01.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.oracle.oBootAPI01.domain.Member;
import com.oracle.oBootAPI01.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

//	전체회원 조회
	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService listMember listMember.size()->" + listMember.size());
		for(Member listMem : listMember) {
			System.out.println("listMem.getName()->"+listMem.getName());
		}
		return listMember;
	}

}
