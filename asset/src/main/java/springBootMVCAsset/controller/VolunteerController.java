package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCAsset.command.VolunteerCommand;
import springBootMVCAsset.service.AutoNumService;

@Controller
@RequestMapping("volunteer")
public class VolunteerController {
	@Autowired
	AutoNumService autoNumService;
	@GetMapping("volunteerRegist1")
	public String volunteerRegist(@RequestParam("announceNum") String announceNum, Model model) {
		String autoNum = autoNumService.execute("receipt_", "receipt_num", 9, "volunteer");
		VolunteerCommand  volunteerCommand = new VolunteerCommand();
		volunteerCommand.setReceiptNum(autoNum);
		volunteerCommand.setAnnounceNum(announceNum);
		model.addAttribute("volunteerCommand", volunteerCommand);
		return "thymeleaf/volunteer/volunteerRegist";
	}
}
