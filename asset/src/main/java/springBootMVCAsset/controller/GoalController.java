package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoalCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.goal.GoalListService;
import springBootMVCAsset.service.goal.GoalRegistService;

@Controller
@RequestMapping("goal")
public class GoalController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GoalRegistService goalRegistService;
	@Autowired
	GoalListService goalListService;
	
	@GetMapping("myGoal")
	public String myGoal() {
		return "thymeleaf/goal/myGoal";
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
	public String goalRegist(@Validated GoalCommand goalCommand, BindingResult result
			, HttpSession session, @RequestParam(value = "goalName", required = false) String goalName) {
		if(result.hasErrors()) {
			return "thymeleaf/goal/goalRegist";
		}
		goalRegistService.execute(goalCommand, session, goalName);
		return "redirect:/myAsset/myAssetPage";
	}
	
}
