package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.command.EmployeeCommand;
import springBootMVCAsset.mapper.EmployeeMapper;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.employee.EmployeeDetailService;
import springBootMVCAsset.service.employee.EmployeeListService;
import springBootMVCAsset.service.employee.EmployeeRegistService;
import springBootMVCAsset.service.employee.EmployeeUpdateService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeRegistService employeeRegistService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeMapper employeeMapper;
	
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
			return "thymeleaf/emp/empRegist";
		}
		employeeRegistService.execute(employeeCommand);
		return "redirect:employeeList";
	}
	@GetMapping("employeeDetail")
	public String employeeDetail(String empNum, Model model) {
		employeeDetailService.execute(empNum,model);
		return "thymeleaf/emp/empDetail";
	}
	@GetMapping("employeeUpdate")
	public String employeeUpdate(String empNum, Model model) {
		employeeDetailService.execute(empNum,model);
		return "thymeleaf/emp/empUpdate";
	}
	@PostMapping("employeeUpdate")
	public String employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result) {
		System.out.println("커맨드"+employeeCommand);
		if (result.hasErrors()) {
			System.out.println(result);
			return "thymeleaf/emp/empUpdate";
		}
		employeeUpdateService.execute(employeeCommand);
		return "redirect:employeeDetail?empNum=" + employeeCommand.getEmpNum();
	}
	@GetMapping("employeeDelete")
	public String employeeDelete(String empNum) {
		employeeMapper.employeeDelete(empNum);
		return "redirect:employeeList"; 
	}
	@GetMapping("departmentList")
	public String departmentList(Model model) {
		return "thymeleaf/department/departmentList";
	}
	
		
		
	
	
}
