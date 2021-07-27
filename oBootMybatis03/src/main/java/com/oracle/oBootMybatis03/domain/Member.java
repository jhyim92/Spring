package com.oracle.oBootMybatis03.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member3")
public class Member {
	@Id
	private Long id;
	private String name;

}
