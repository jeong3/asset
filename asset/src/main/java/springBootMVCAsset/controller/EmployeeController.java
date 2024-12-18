package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.command.EmployeeCommand;
import springBootMVCAsset.service.employee.EmployeeListService;
import springBootMVCAsset.service.employee.EmployeeRegistService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeRegistService employeeRegistService;
	
	@GetMapping("employeeList")
	public String employeeList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/emp/empList";
	}
	@GetMapping("employeeRegist")
	public String employeeRegist(EmployeeCommand employeeCommand) {
		employeeRegistService.execute(employeeCommand);
		return "thymeleaf/emp/empRegist";
	}
}
