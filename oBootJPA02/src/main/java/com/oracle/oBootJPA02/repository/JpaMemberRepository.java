package com.oracle.oBootJPA02.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.oracle.oBootJPA02.domain.Member;
import com.oracle.oBootJPA02.domain.Team;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
//		팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);

//		회원저장
//		persist : 저장하는 메소드, member.setTeam(team); // 단반향 연관관계 설정, 참조 저장
		member.setTeam(team);
		em.persist(member);
		return null;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

	// Optional 객체를 사용하면 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피.
	// 즉, 복잡한 조건문 없이도 널(null) 값으로 인해 발생하는 예외를 처리
	@Override
	public Optional<Member> findByMember(Long id) {
//		em.find(Member.class, id) -> select * from member where id = id;
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		if (member3 != null) {
//			팀저장
			Team team = em.find(Team.class, member.getTeamid());
			if (team != null) {
				team.setName(member.getTeamname());
				em.persist(team);
			}
//			회원저장
			member3.setTeam(team);
			member3.setName(member.getName());
			em.persist(member3);
			System.out.println("JpaMemberRepository updateByMember member.getName()->" + member.getName());
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist..");
		}
		return result;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname = name + '%';
		System.out.println("");

		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class)
				.setParameter("name", pname).getResultList();
		System.out.println("JpaMemberRepository memberList.size()->" + memberList.size());
		return memberList;
	}

}
