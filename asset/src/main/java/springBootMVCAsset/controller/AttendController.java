package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.service.attend.AttendListService;

@Controller
@RequestMapping("attend")
public class AttendController {
	@Autowired
	AttendListService attendListService;
	@GetMapping("attend")
	public String attend(String empNum, Model model) {
		attendListService.execute(empNum, model);
		model.addAttribute("empNum", empNum);
		return "thymeleaf/attend/attend";
	}
}
