package com.oracle.oBootMybatis03.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVo;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list start...");
		int total = es.total();
		System.out.println("EmpController total->" + total);
		System.out.println("EmpController currentPage->" + currentPage);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart()); // 시작시 1
		emp.setEnd(pg.getEnd()); // 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController List<Emp> listEmp.size()->" + listEmp.size());

		model.addAttribute("total", total);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		return "list";
	}

	@GetMapping(value = "detail")
	public String detail(HttpServletRequest request, int empno, Model model) {
		System.out.println("EmpController Start detail...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);

		return "detail";
	}

	@GetMapping("updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController String updateForm start...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		return "updateForm";
	}

//	@RequestMapping(value="update", method=RequestMethod.POST)
	@PostMapping("update")
	public String update(Emp emp, Model model) {
		System.out.println("EmpController String update start...");
		int k = es.update(emp);
		System.out.println("EmpController String update es.update(emp) k->" + k);
		model.addAttribute("kkk", k); // Test Controller간 Data 전달
		model.addAttribute("kk3", "forward Test");

//		return "redirect:list";
//		forward = Controller간 Data 전달할떄 쓰면 kkk의 데이터 값이 list로 넘어감
		return "forward:list";
	}

	@RequestMapping("writeForm")
	public String writeForm(Model model) {
		System.out.println("EmpController String writeForm start...");
		List<Emp> list = es.listManager();
		System.out.println("EmpController String writeForm list.size()->" + list.size());
		model.addAttribute("empMngList", list); // emp Manager List
		List<Dept> deptList = es.deptSelect();
		System.out.println("EmpController String writeForm deptList.size()->" + deptList.size());
		model.addAttribute("deptList", deptList); // dept
		return "writeForm";
	}

	@PostMapping("write")
	public String write(Emp emp, Model model) {
		System.out.println("EmpController String write start...");
		int result = es.insert(emp);
		if (result > 0) {
			return "redirect:list";
		} else {
			model.addAttribute("msg", "땡! 입력실패!");
			return "forward:writeForm";
		}
	}

	@GetMapping("confirm")
	public String confirm(int empno, Model model) {
		System.out.println("EmpController String confirm start...");
		Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if (emp != null) {
			model.addAttribute("msg", "땡! 중복입니다.");
			return "forward:writeForm";
		} else {
			model.addAttribute("msg", "사용 가능한 사번입니다.");
			return "forward:writeForm";
		}
	}

	@RequestMapping("delete")
	public String delete(int empno, Model model) {
		System.out.println("EmpController String delete start...");
		int k = es.delete(empno);
		System.out.println("EmpController String delete int k->" + k);
		return "redirect:list";
	}

	@GetMapping("listEmpDept")
	public String listEmpDept(Model model) {
		System.out.println("EmpController String listEmpDept start...");
		EmpDept empDept = null;
		List<EmpDept> listEmpDept = es.listEmpDept();
		System.out.println("EmpController String listEmpDept listEmpDept.size()->" + listEmpDept.size());
		model.addAttribute("listEmpDept", listEmpDept);
		return "listEmpDept";
	}

	@GetMapping("mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("EmpController String mailTransport start...");
		String tomail = "tkghlwhfu@gmail.com"; // 받는 사람 이메일
		System.out.println("tomail->" + tomail);
		String setfrom = "jhyim92@gmail.com"; // 보내는 사람 이메일
		String title = "mailTransport 입니다"; // 제목
		try {
//			Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보내는 사람 생략하면 정삭적으로 작동 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일 제목은 생략가능
//			tempPassword = 임시 패스워드
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호 입니다 : " + tempPassword); // 메일내용
			System.out.println("임시 비밀번호 입니다 : " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
//			첨부문서 Attachment = 부착물
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1); // 정상전달

		} catch (Exception e) {
			System.out.println("EmpController String mailTransport Exception->" + e.getMessage());
			model.addAttribute("check", 2); // 메일전달 실패
		}
		return "mailResult";
	}

//	Procedure Test 입력화면
	@GetMapping("writeDeptIn")
	public String writeDeptIn(Model model) {
		System.out.println("EmpController String writeDeptIn start...");
		return "writeDept3";
	}

//	Procedure Test 입력후 VO 전달
	@PostMapping("writeDept")
	public String writeDept(DeptVo deptVo, Model model) {
		System.out.println("EmpController POST String writeDept start...");
		es.insertDept(deptVo); // Procedure Call
		if (deptVo == null) {
			System.out.println("deptVo NULL");
		} else {
			System.out.println("RdeptVo.getOdeptno()->" + deptVo.getOdeptno());
			System.out.println("RdeptVo.getOdname()->" + deptVo.getOdname());
			System.out.println("RdeptVo.getOloc()->" + deptVo.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다.");
			model.addAttribute("dept", deptVo);
		}
		return "writeDept3";
	}

	@GetMapping("writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController HashMap String writeDeptCursor start...");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 80);
		es.selListDept(map);
		List<Dept> deptLists = (List<Dept>) map.get("dept");
		for (Dept dept : deptLists) {
			System.out.println("writeDeptCursor dept.getDname->" + dept.getDname());
			System.out.println("writeDeptCursor dept.getLoc->" + dept.getLoc());
		}
		System.out.println("EmpController writeDeptCursor deptList.size()->" + deptLists.size());
		model.addAttribute("deptList", deptLists);

		return "writeDeptCursor";
	}

//	interCepter 시작화면
	@RequestMapping("interCepterForm")
	public String interCepterForm(Model model) {
		System.out.println("EmpController interCepterForm start...");
		return "interCepterForm";
	}

//	interCepter 진행 Test 2번
	@RequestMapping("interCepter")
	public String interCepter(String id, Model model) {
		System.out.println("EmpController String interCepter Test start...2번");
		System.out.println("EmpController interCepter id->" + id);
		int memCnt = es.memCount(id);
		System.out.println("EmpController memCnt->" + memCnt);

		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		System.out.println("EmpController interCepter Test End");
		return "interCepter"; // User 존재하면 User 이용 조회 Page
	}

//	interCepter 진행 Test 4번
	@RequestMapping("doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		System.out.println("EmpController String doMemberList start...4번");
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("EmpController String doMemberList id->" + ID);
		Member1 member1 = null;
//		Member1 List Get Service
		List<Member1> listMem = es.listMem(member1);
		System.out.println("EmpController String doMemberList listMem.size()->" + listMem.size());
		model.addAttribute("ID", ID);
		model.addAttribute("listMem", listMem);
		return "doMemberList"; // User 존재하면 User 이용 조회 Page
	}

//	SampleInterceptor 내용을 받아 처리
	@RequestMapping("doMemberWrite")
	public String doMemberWrite(Model model, HttpServletRequest request) {
		System.out.println("EmpController String doMemberWrite start...");
		String ID = (String) request.getSession().getAttribute("ID");
		model.addAttribute("id", ID);
		return "doMemberWrite";
	}

//	Ajax Test
	@RequestMapping(value = "getDeptName", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getDeptName(int deptno, Model model) {
		System.out.println("EmpController String getDeptName start...");
		System.out.println("deptno->" + deptno);
		return es.deptName(deptno);
	}

//	Ajax List Test
	@RequestMapping("listEmpAjax")
	public String listEmpAjax(Model model) {
		System.out.println("EmpController String listEmpAjax start...");
		EmpDept empDept = null;
		List<EmpDept> listEmp = es.listEmp(empDept);
		System.out.println("EmpController String listEmpAjax listEmp.size()->"+listEmp.size());
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjax";
	}
	
//	Ajax List Test
	@RequestMapping("listEmpAjax2")
	public String listEmpAjax2(Model model) {
		System.out.println("EmpController String listEmpAjax2 start...");
		EmpDept empDept = null;
		List<EmpDept> listEmp = es.listEmp(empDept);
		System.out.println("EmpController String listEmpAjax listEmp.size()->"+listEmp.size());
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);
		return "listEmpAjax2";
	}

}
