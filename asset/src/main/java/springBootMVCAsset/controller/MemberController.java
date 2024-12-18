package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.service.AutoNumService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	AutoNumService autoNumService;
	
	@GetMapping("memberRegist")
	public String memberRegist() {
		//String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
		return "thymeleaf/member/memberRegist";
	}
}
