package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.MemberCommand;
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
	public String memberMyPwUpdate(String oldPw, String newPw, String newPWCon, HttpSession session) {
		memberMyPwUpdateService.execute(oldPw, newPw, newPWCon, session);
		return "redirect:memberMyDetail";
	}
}
