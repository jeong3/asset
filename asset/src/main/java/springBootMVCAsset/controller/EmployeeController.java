package springBootMVCAsset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@GetMapping("employeeList")
	public String employeeList() {
		
		return "thymeleaf/emp/empList";
	}
}
