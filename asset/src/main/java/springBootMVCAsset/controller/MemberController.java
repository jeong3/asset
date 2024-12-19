package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.member.MemberDeleteService;
import springBootMVCAsset.service.member.MemberDetailService;
import springBootMVCAsset.service.member.MemberListService;
import springBootMVCAsset.service.member.MemberRegistService;
import springBootMVCAsset.service.member.MemberUpdateService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	MemberRegistService memberRegistService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@GetMapping("memberRegist")
	public String memberRegist(Model model) {
		String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
		MemberCommand memberCommand = new MemberCommand();
		memberCommand.setMemberNum(autoNum);
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/member/memberRegist";
	}
	
	@PostMapping("memberRegist")
	public String memberRegist(@Validated MemberCommand memberCommand, BindingResult result) { // 오버로딩
		if(result.hasErrors()) {
			return "thymeleaf/member/memberRegist";
		}
		if(!memberCommand.isMemberPwEqualmemberPwCon()) {
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon", "비밀번호가 일치하지 않음");
			return "thymeleaf/member/memberForm";
		}
		memberRegistService.execute(memberCommand);
		return "redirect:memberList";
	}
	
	@GetMapping("memberList")
	public String memberList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberDetail";
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberUpdate";
	}
	
	@PostMapping("memberUpdate")
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberUpdate";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail?memberNum=" + memberCommand.getMemberNum();
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:/member/memberList";
	}
} 
