package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.LoginCommand;
import springBootMVCAsset.service.login.UserLoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	UserLoginService userLoginService;
	
	@GetMapping("login")
	public String String(LoginCommand loginCommand) {
		return "thymeleaf/login";
	}
	
	@PostMapping("login")
	public String login(@Validated LoginCommand loginCommand, BindingResult result
			, HttpSession session) {
		userLoginService.execute(loginCommand, result, session);
		if(result.hasErrors()) {
			return "thymeleaf/login";
		}
		return "redirect:/";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
