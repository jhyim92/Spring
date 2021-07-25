package com.oracle.oBootJPA02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

//Entity : 테이블을 만들기위한 객체
@Entity 				// 객체에서 사용할 시퀀스 이름		 		db에 저장될 시퀀스 이름			초기값 			시퀀스 한번 호출에 증가하는 수
@SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq_generator", initialValue = 1, allocationSize = 1)
@Table(name = "member1")
public class Member {
//	@Id : 기본 키 Mapping 방법 직접 할당
	@Id
//	자동 생성(@GeneratedValue)  strategy : 전략 generator : 생성
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	private Long id;
//	필드와 매핑할 테이블의 컬럼 이름
	@Column(name = "username")
	private String name;

//	@ManyToOne  다 대 일 관계
	@ManyToOne
//	외래 키를 매핑할 때 사용하는 @Joincolumn
	@JoinColumn(name = "team_id")
	private Team team;
	
	// @Transient 데이터베이스에 저장하지 않음, 조회하지 않음 ,주로 메모리상에서맊 임시로 어떤 값을 보관하고 싶을 때 사용:
//	Update시->Team에 저장할 teamname 임시저장, TBL에는 존재 안하고 임시 버퍼에 사용
	@Transient
	private String teamname;
	
	@Transient
	@Column(name = "team_id")
	private Long teamid;

	public Long getTeamid() {
		return teamid;
	}

	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
