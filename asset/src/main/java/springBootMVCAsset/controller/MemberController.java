package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.service.AutoNumService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	AutoNumService autoNumService;
	
	@GetMapping("memberRegist")
	public String memberRegist(Model model) {
		//String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
		//MemberCommand memberCommand = new MemberCommand();
		//memberCommand.setMemberNum(autoNum);
		//model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/member/memberRegist";
	}
}
