package com.oracle.oBootMybatis03.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

//	upLoadForm 시작화면
	@RequestMapping("upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("UploadController String upLoadFormStart start...");
		return "upLoadFormStart";
	}

	@RequestMapping("uploadForm")
	public void uploadForm() {
		System.out.println("UploadController void uploadForm() start...");
		System.out.println();
	}

	@PostMapping("uploadForm")
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model) throws IOException {
		System.out.println("UploadController Post String uploadForm start...");
//		uploadPath = 파일경로지정
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		logger.info("originalName : " + file1.getOriginalFilename());
		logger.info("size : " + file1.getSize());
		logger.info("contentType : " + file1.getContentType());
		logger.info("uploadPath : " + uploadPath);
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		logger.info("saveName : " + savedName);
		model.addAttribute("savedName", savedName);
		return "uploadResult";
	}

	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws IOException {
		System.out.println("UploadController String uploadFile start...");
//		UUID = 범용 고유 식별자는 소프트웨어 구축에 쓰이는 식별자 표준으로, 개방 소프트웨어 재단이 분산 컴퓨팅 환경의 일부로 표준화하였다
		UUID uid = UUID.randomUUID();
//		requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath->" + uploadPath);
//		Directory 생성
		File fileDirectory = new File(uploadPath);
//		fileDirectory 가 없으면 만들어준다
		if (!fileDirectory.exists()) {
//			mkdirs = make directory의 약자
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}

		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target); // org.springframework.util.FileCopyUtils

		return savedName;
	}

	@RequestMapping("uploadFileDelete")
	public String uploadFileDelete(HttpServletRequest request, Model model) {
		System.out.println("UploadController POST String uploadFileDelete Start...");
//		uploadPath = 파일경로지정
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
//		deleteFile = 지정된경로 + 파일 이름
		String deleteFile = uploadPath + "e267cba3-3f4e-45cf-abff-e7085aa07ad0_jung1.jpg";
		logger.info("deleteFile : " + deleteFile);
		int delResult = upFileDelete(deleteFile);
		logger.info("deleteFile Result->" + delResult);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		return "uploadResult";
	}

	private int upFileDelete(String deleteFileName) {
		System.out.println("UploadController int upFileDelete Start...");
		int result = 0;
		logger.info("upFileDelete result->" + deleteFileName);
		File file = new File(deleteFileName);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("파일삭제 성공!");
				result = 1;
			} else {
				System.out.println("파일삭제 실패");
				result = 0;
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}

}
