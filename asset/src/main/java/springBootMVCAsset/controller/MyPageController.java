package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.service.myPage.MemberMyDetailService;

@Controller
public class MyPageController {
	@Autowired
	MemberMyDetailService memberMyDetailService;
	
	@GetMapping("memberMyDetail")
	public String memberMyDetail(HttpSession session, Model model) {
		memberMyDetailService.execute(session, model);
		return "thymeleaf/myPage/memberMyDetail";
	}
}
