package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.command.EmployeeCommand;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.employee.EmployeeListService;
import springBootMVCAsset.service.employee.EmployeeRegistService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeRegistService employeeRegistService;
	@Autowired
	AutoNumService autoNumService;
	
	@GetMapping("employeeList")
	public String employeeList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/emp/empList";
	}
	@GetMapping("employeeRegist")
	public String empRegist(String sep, String columnName, Integer len, String tableName, Model model,
			EmployeeCommand employeeCommand) {
		String empNum = autoNumService.execute("emp_", "emp_num", 5, "employees");
		employeeCommand.setEmpNum(empNum);
		model.addAttribute("employeeCommand", employeeCommand);
		return "thymeleaf/emp/empRegist";
	}
	@PostMapping("employeeRegist")
	public String employeeRegist(@Validated EmployeeCommand employeeCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/emp/empRegist";
		}
		if (!employeeCommand.isEmpPwEqualEmpPwCon()) {
			// model.addAttribute("errPw", "비밀번호가 일치하지 않습니다.");
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호가 일치하지 않습니다.");
			return "redirect:employeeList";
		}
		employeeRegistService.execute(employeeCommand);
		return "thymeleaf/emp/empRegist";
	}
	
}
