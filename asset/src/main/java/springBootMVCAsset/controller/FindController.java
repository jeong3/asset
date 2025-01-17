package springBootMVCAsset.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCAsset.command.PwChangeCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.FindMapper;
import springBootMVCAsset.service.find.FindIdService;
import springBootMVCAsset.service.find.FindPwService;
import springBootMVCAsset.service.find.PwChangeService;

@Controller
@RequestMapping("find")
public class FindController {
	@Autowired
	PwChangeService pwChangeService;
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
	public String findPassword(String userId, String userPhone, Model model) {
	    // 로그 출력
	    System.out.println("아이디 : " + userId);
	    System.out.println("번호 : " + userPhone);

	    // Map 객체를 사용하여 파라미터 전달
	    Map<String, String> params = new HashMap<>();
	    params.put("_userId", userId);
	    params.put("_userPhone", userPhone);

	    // MyBatis mapper 호출
	    AuthInfoDTO auth = findMapper.compare(params);
	    // 결과 확인 후 적절한 뷰로 이동
	    System.out.println("auth : " + auth);
	    if (auth != null) {
	    	model.addAttribute("grade", auth.getGrade());
	    	model.addAttribute("userNum", auth.getUserNum());
	        return "thymeleaf/find/changePw";
	    }
	    return "thymeleaf/find/findPwFail";
	}
	@PostMapping("pwChange")
	public String pwChange(PwChangeCommand pwChangeCommand) {
		System.out.println("출력");
		System.out.println("pwChangeCommand : " + pwChangeCommand);
		int i = pwChangeService.execute(pwChangeCommand);
		if(i == 1) {
			return "thymeleaf/find/pwChangeOk";
		}else{
			return "redirect:memberMyPwUpdate";
		}
	}
}
