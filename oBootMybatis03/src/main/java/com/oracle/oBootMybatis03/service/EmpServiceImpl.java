package com.oracle.oBootMybatis03.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.dao.Member1Dao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVo;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	@Autowired
	private Member1Dao md;

	@Override
	public int total() {
		System.out.println("EmpServiceImpl total start...");
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total() totCnt->" + totCnt);
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl List<Emp> listEmp start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl List<Emp> empList.size()->" + empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl Emp detail start...");
		Emp emp = null;
		emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceImpl int update start...");
		int kkk = 0;
		kkk = ed.update(emp);
		return kkk;
	}

//	관리자 emp List
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpServiceImpl List<Emp> listManager start...");
		List<Emp> empList = null;
		empList = ed.listManager();
		System.out.println("EmpServiceImpl List<Emp> listManager empList.size()->" + empList.size());
		return empList;
	}

//	부서 List
	@Override
	public List<Dept> deptSelect() {
		System.out.println("EmpServiceImpl List<Dept> deptSelect() start...");
		List<Dept> deptList = null;
		deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl List<Dept> deptSelect deptList.size()->" + deptList.size());
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		System.out.println("EmpServiceImpl int insert start...");
		int result = 0;
		result = ed.insert(emp);
		System.out.println("EmpServiceImpl int insert result->" + result);
		return result;
	}

	@Override
	public int delete(int empno) {
		System.out.println("EmpServiceImpl int delete start...");
		int result = 0;
		result = ed.delete(empno);
		System.out.println("EmpServiceImpl int delete result->" + result);
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl List<EmpDept> listEmpDept start...");
		List<EmpDept> empDeptList = null;
		empDeptList = ed.listEmpDept();
		System.out.println("EmpServiceImpl List<EmpDept> listEmpDept empDeptList.size()->" + empDeptList.size());
		return empDeptList;
	}

	@Override
	public void insertDept(DeptVo deptVo) {
		System.out.println("EmpServiceImpl void insertDept start...");
		dd.insertDept(deptVo);
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		System.out.println("EmpServiceImpl void selListDept start...");
		dd.selListDept(map);
	}

	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl int memCount start...");
		System.out.println("EmpServiceImpl int memCount id->"+id);
		return md.memCount(id);
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl List<Member1> listMem start...");
		return md.listMem(member1);
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl String deptName start...");
		return ed.deptName(deptno);
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpServiceImpl List<EmpDept> listEmp start...");
		return ed.listEmp(empDept);
	}

}
