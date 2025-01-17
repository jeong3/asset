package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCAsset.mapper.FindMapper;
import springBootMVCAsset.service.find.FindIdService;
import springBootMVCAsset.service.find.FindPwService;

@Controller
@RequestMapping("find")
public class FindController {

	@Autowired
	FindMapper findMapper;
	@Autowired
	FindPwService findPwService;
	@Autowired
	FindIdService findIdService;
	@RequestMapping(value="findId", method = RequestMethod.GET)
	public String findId() {
		return "thymeleaf/find/findId";
	}
	@RequestMapping(value="findId", method = RequestMethod.POST)
	public String findId(
			@RequestParam("userPhone")String userPhone,
			@RequestParam("userEmail")String userEmail,
			Model model) {
		findIdService.execute(userPhone, userEmail, model);
		return "thymeleaf/find/findIdOk";
	}
	@GetMapping("findPassword")
	public String findPassword() {
		return "thymeleaf/find/findPw";
	}
	@PostMapping("findPassword")
	public String findPassword(String userId,String userPhone,Model model) {
		System.out.println("아이디 : " + userId);
		System.out.println("번호 : " + userPhone);
		String Pw = findMapper.compare(userId, userPhone);
		
		System.out.println("비밀번호 : " + Pw);
		if(Pw != null) {
			return "thymeleaf/find/changePw";
		}
		return "thymeleaf/find/findPwFail";
	}
}
