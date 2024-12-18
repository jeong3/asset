package springBootMVCAsset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	@GetMapping("employeeList")
	public String employeeList() {
		
		return "thymeleaf/empList";
	}
}
