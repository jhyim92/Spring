package com.oracle.oBootMybatis03.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.oracle.oBootMybatis03.domain.Member;

public class MemberJpaRepositoryImpl implements MemberJpaRepository {

	private final EntityManager em;

	public MemberJpaRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		System.out.println("MemberJpaRepositoryImpl Member save start...4번");
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		System.out.println("MemberJpaRepositoryImpl List<Member> findAll start...");
		List<Member> memberList = em.createQuery("select m from Member m",Member.class).getResultList();
		return memberList;
	}

	@Override
	public Optional<Member> findById(Long id) {
		System.out.println("MemberJpaRepositoryImpl Optional<Member> findById start...");
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		System.out.println("MemberJpaRepositoryImpl void updateByMember start...");
		em.merge(member);
		return;
	}

}
