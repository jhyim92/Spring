package com.oracle.oBootDBConnect.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.oracle.oBootDBConnect.domain.Member;

public class jdbcTemplateMemberRepository implements MemberRepository {
	private final JdbcTemplate jdbcTemplate;

	public jdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	

	
	@Override
	public Member save(Member member) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", member.getId());
		parameters.put("name", member.getName());
		jdbcInsert.execute(parameters);


		return member;
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from member", memberrRowMapper());
	}

	private RowMapper<Member> memberrRowMapper() {
		// 확장  for문 이랑 비슷 rs = ResultSet
		// 람다함수
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
	}

}
