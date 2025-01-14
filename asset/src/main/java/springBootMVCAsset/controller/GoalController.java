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
import springBootMVCAsset.service.goal.GoalDetailService;
import springBootMVCAsset.service.goal.GoalFinishListService;
import springBootMVCAsset.service.goal.GoalRegistService;
import springBootMVCAsset.service.goal.GoalRunListService;

@Controller
@RequestMapping("goal")
public class GoalController {
	@Autowired
	GoalRegistService goalRegistService;
	@Autowired
	GoalRunListService goalRunListService;
	@Autowired
	GoalFinishListService goalFinishListService;
	@Autowired
	GoalDetailService goalDetailService;
	
	@GetMapping("myGoal")
	public String myGoal(HttpSession session, Model model) {
		goalRunListService.execute(session, model);
		goalFinishListService.execute(session, model);
		return "thymeleaf/goal/myGoal";
	}
	
	@GetMapping("goalRegist")
	public String goalRegist(@RequestParam(value="goalName", required = false) String goalName
			, Model model) {
		GoalCommand goalCommand = new GoalCommand();
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
		return "redirect:/goal/myGoal";
	}
	
	@GetMapping("goalDetail")
	public String goalDetail(@RequestParam("goalNum") String goalNum, Model model) {
		goalDetailService.execute(goalNum, model);
		return "thymeleaf/goal/goalDetail";
	}
	
}
