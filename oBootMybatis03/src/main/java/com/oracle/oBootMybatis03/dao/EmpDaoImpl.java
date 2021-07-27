package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;

@Repository
public class EmpDaoImpl implements EmpDao {

//	mybatis 는 SqlSession을 쓴다.
	@Autowired
	private SqlSession session;

	@Override
	public int total() {
		int tot = 0;
		System.out.println("EmpDaoImpl total() start...");
		try {
//				session-> Mapper ID total 호출
			tot = session.selectOne("total");
			System.out.println("EmpDaoImpl total() tot->" + tot);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl total() Exception->" + e.getMessage());
		}
		return tot;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl List<Emp> listEmp start...");
		try {
//			Naming Rule을 지킬려고 tkEmpListAll 이렇게 만듬
//			tkEmpListAll = mapper Id이다
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl List<Emp> listEmp(Emp emp) empList.size()->" + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl List<Emp> listEmp Exception->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpDaoImpl Emp detail start...");
		Emp emp = new Emp();
		try {
//						tkEmpSelOne = mapper Id   empno = Parameter
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl Emp detail emp.getEname()->" + emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl Emp detail Exception ->" + e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl int update start...");
		int kkk = 0;
		try {
			kkk = session.update("tkEmpUpdate", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl int update Exception ->" + e.getMessage());
		}
		return kkk;
	}

//	emp 관리자만 select
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpDaoImpl List<Emp> listManager start...");
		List<Emp> empList = null;
		try {
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl List<Emp> listManager Exception ->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public int insert(Emp emp) {
		System.out.println("EmpDaoImpl int insert start...");
		int result = 0;
		try {
			result = session.insert("insert", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl int insert Exception ->" + e.getMessage());
		}

		return result;
	}

	@Override
	public int delete(int empno) {
		System.out.println("EmpDaoImpl int delete start...");
		int result = 0;
		try {
			result = session.delete("delete", empno);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl int delete Exception ->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpDaoImpl List<EmpDept> listEmpDept start...");
		List<EmpDept> empDept = null;
		try {
			empDept = session.selectList("listEmpDept");
			System.out.println("EmpDaoImpl List<EmpDept> listEmpDept empDept->" + empDept);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl List<EmpDept> listEmpDept Exception ->" + e.getMessage());
		}
		return empDept;
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl String deptName start...");
		return session.selectOne("tkDeptName", deptno);
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpDaoImpl List<EmpDept> listEmp start...");
		return session.selectList("listEmpDept",empDept);
	}

}
