package springBootMVCAsset.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.InquireDTO;
import springBootMVCAsset.mapper.InquireMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	MemberMapper memberMapper;
	@GetMapping("inquireWrite")
	public String inquireWrite(
			@ModelAttribute(value="goodsNum") String goodsNum) {
		return "thymeleaf/inquire/inquireRegist";
	}
	@PostMapping("inquireWrite")
	public void inquireWrite(InquireDTO inquireDTO
			, HttpSession session, HttpServletResponse response) {
		AuthInfoDTO auth = (AuthInfoDTO	)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		inquireDTO.setMemberNum(memberNum);
		inquireMapper.inquireInsert(inquireDTO);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>";
				   str += "window.self.close()";
				   str += "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
