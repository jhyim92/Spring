package com.oracle.oBootAPI01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootAPI01.domain.Member;
import com.oracle.oBootAPI01.repository.MemberRepository;

@SpringBootTest
class OBootApi01ApplicationTests {
	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false) // 이걸쓰면 sql에 저장됨
	public void testMember() {
		Member member = new Member();
		member.setName("memberTestA");
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.findByMember(saveId);
		System.out.println("OBootApi01ApplicationTests saveId->"+saveId);
		System.out.println("OBootApi01ApplicationTests findMember.getId()->"+findMember.getId());
//		정상적
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//		에러발생
		Assertions.assertThat(findMember.getId()).isEqualTo(20);
		
	}

}
