package com.oracle.oBootJPA02;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootJPA02.repository.JpaMemberRepository;
import com.oracle.oBootJPA02.repository.MemberRepository;
import com.oracle.oBootJPA02.service.MemberService;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;

	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new JpaMemberRepository(em);
	}

}
