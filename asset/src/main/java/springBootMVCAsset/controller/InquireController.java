package springBootMVCAsset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		System.out.println("memberNum값 : " + memberNum );
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
	@GetMapping("inquireUpdate")
	public String inquireUpdate(Integer inquireNum, Model model ) {
		List<InquireDTO> list = inquireMapper.inquireList(null,inquireNum);
		model.addAttribute("list", list);
		return "thymeleaf/inquire/goodsInquireUpdate"; 
	}
	@PostMapping("inquireUpdate")
	@ResponseBody
	public void inquireUpdate(InquireDTO inquireDTO , HttpServletResponse response) {
		inquireMapper.inquireUpdate(inquireDTO);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>"
					   + "opener.parent.inquire();" 
					   + "window.self.close()" 
					   + "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "thymeleaf/inquire/goodsInquireClose"; 
	}
	@RequestMapping("inquireDelete")
	public @ResponseBody void inquireDelete(Integer inquireNum) {
		inquireMapper.inquireDelete(inquireNum);
	}
	@RequestMapping("goodsQuestion")
	public String goodsQuestion(Model model) {
		List<InquireDTO> list = inquireMapper.inquireList(null, null);
		model.addAttribute("list", list);
		return "thymeleaf/inquire/goodsQuestion";
	}
	@GetMapping("inquireAnswer")
	public String inquireAnswer(Integer inquireNum, Model model) {
		List<InquireDTO> list = inquireMapper.inquireList(null, inquireNum);
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/inquireAnswer";
	}
	@PostMapping("inquireAnswer")
	public String inquireAnswer(InquireDTO inquireDTO, HttpSession  session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empNum = auth.getUserNum();
		inquireDTO.setEmpNum(empNum);
		inquireMapper.inquireAnswerUpdate(inquireDTO);
		return "redirect:goodsQuestion";
	}
}
