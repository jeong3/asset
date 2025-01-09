package springBootMVCAsset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("volunteer")
public class VolunteerController {
	@GetMapping
	public String volunteerDetail() {
		return "thymeleaf/volunteer/volunteerDetail";
	}
}
