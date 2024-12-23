package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.service.myPage.MemberMyDeleteService;
import springBootMVCAsset.service.myPage.MemberMyDetailService;
import springBootMVCAsset.service.myPage.MemberMyPwUpdateService;
import springBootMVCAsset.service.myPage.MemberMyUpdateService;

@Controller
@RequestMapping("myPage")
public class MyPageController {
	@Autowired
	MemberMyDetailService memberMyDetailService;
	@Autowired
	MemberMyUpdateService memberMyUpdateService;
	@Autowired
	MemberMyPwUpdateService memberMyPwUpdateService;
	@Autowired
	MemberMyDeleteService memberMyDeleteService;
	
	@GetMapping("memberMyDetail")
	public String memberMyDetail(HttpSession session, Model model) {
		memberMyDetailService.execute(session, model);
		return "thymeleaf/myPage/memberMyDetail";
	}
	
	@GetMapping("memberMyUpdate")
	public String memberMyUpdate(HttpSession session, Model model) {
		memberMyDetailService.execute(session, model);
		return "thymeleaf/myPage/memberMyUpdate";
	}
	
	@PostMapping("memberMyUpdate")
	public String memberMyUpdate(MemberCommand memberCommand,
			HttpSession session) {
		memberMyUpdateService.execute(memberCommand, session);
		return "redirect:memberMyDetail";
	}
	
	@GetMapping("memberMyPwUpdate")
	public String memberMyPwUpdate() {
		return "thymeleaf/myPage/memberMyPwUpdate";
	}
	
	@PostMapping("memberMyPwUpdate")
	public String memberMyPwUpdate(String oldPw, String newPw, String newPwCon, HttpSession session) {
		int i = memberMyPwUpdateService.execute(oldPw, newPw, newPwCon, session);
		if(i == 1) {
			return "redirect:memberMyDetail";
		}else{
			return "redirect:memberMyPwUpdate";
		}
	}
	
	@PostMapping("newPwCheck")
	public @ResponseBody Integer newPwCheck(String newPw, String newPwCon){
		if(newPw.equals(newPwCon)) {
			return 1;
		}else {return 0;}
	}
	
	@GetMapping("memberMyDelete")
	public String memberMyDelete() {
		return "thymeleaf/myPage/memberMyDelete";
	}
	
	@PostMapping("memberMyDelete")
	public String memberMyDelete(String memberPw, HttpSession session) {
		memberMyDeleteService.execute(memberPw, session);
		return "redirect:/login/logout";
	}
}
