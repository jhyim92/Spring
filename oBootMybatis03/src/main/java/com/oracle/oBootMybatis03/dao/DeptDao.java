package com.oracle.oBootMybatis03.dao;

import java.util.List;
import java.util.Map;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVo;

public interface DeptDao {
	List<Dept> deptSelect();
	void insertDept(DeptVo deptVo);
	void selListDept(Map<String, Object> map);
}
