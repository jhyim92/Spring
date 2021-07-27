package com.oracle.oBootMybatis03.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVo;

@Repository
public class DeptDaoImpl implements DeptDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<Dept> deptSelect() {
		System.out.println("DeptDaoImpl List<Dept> deptSelect start...");
		List<Dept> deptList = null;
		deptList = session.selectList("tkSelectDept");
		System.out.println("DeptDaoImpl List<Dept> deptSelect deptList.size()->" + deptList.size());
		return deptList;
	}

	@Override
	public void insertDept(DeptVo deptVo) {
		System.out.println("DeptDaoImpl void insertDept start...");
		session.selectOne("ProcDept", deptVo);
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		System.out.println("DeptDaoImpl void selListDept start...");
		session.selectOne("ProcDeptList",map);
	}

}
