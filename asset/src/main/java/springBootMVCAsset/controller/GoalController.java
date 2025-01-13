package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootMVCAsset.command.GoalCommand;
import springBootMVCAsset.service.AutoNumService;

@Controller
@RequestMapping("goal")
public class GoalController {
	@Autowired
	AutoNumService autoNumService;
	
	@GetMapping("goalChoose")
	public String goalChoose() {
		return "thymeleaf/goal/goalChoose";
	}
	
	@GetMapping("goalRegist")
	public String goalRegist(@RequestParam(value="goalName", required = false) String goalName
			, Model model) {
		String autoNum = autoNumService.execute("goal_", "goal_num", 6, "goal");
		GoalCommand goalCommand = new GoalCommand();
		goalCommand.setGoalNum(autoNum);
		goalCommand.setGoalName(goalName);
		model.addAttribute("goalCommand", goalCommand);
		return "thymeleaf/goal/goalRegist";
	}
	
	@PostMapping("goalRegist")
	public String goalRegist() {
		return "redirect:/myAsset/myAssetPage";
	}
}
