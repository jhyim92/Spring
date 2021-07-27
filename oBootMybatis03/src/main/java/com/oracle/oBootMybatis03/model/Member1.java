package com.oracle.oBootMybatis03.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member1 {
	private String Id;
	   private String password;
	   private String name;
	   private Date reg_date;

}
