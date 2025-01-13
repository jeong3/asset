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

import springBootMVCAsset.command.DepartmentCommand;
import springBootMVCAsset.command.EmployeeCommand;
import springBootMVCAsset.command.EvalCommand;
import springBootMVCAsset.mapper.EmployeeMapper;
import springBootMVCAsset.service.AutoNumService;
import springBootMVCAsset.service.department.DepartmentDetailService;
import springBootMVCAsset.service.department.DepartmentListService;
import springBootMVCAsset.service.department.DepartmentRegistService;
import springBootMVCAsset.service.department.DepartmentUpdateService;
import springBootMVCAsset.service.employee.EmployeeDetailService;
import springBootMVCAsset.service.employee.EmployeeListService;
import springBootMVCAsset.service.employee.EmployeeRegistService;
import springBootMVCAsset.service.employee.EmployeeUpdateService;
import springBootMVCAsset.service.eval.EvalDeleteService;
import springBootMVCAsset.service.eval.EvalInfoService;
import springBootMVCAsset.service.eval.EvalRegistService;
import springBootMVCAsset.service.eval.EvalUpdateService;
import springBootMVCAsset.service.eval.SalarySelectService;

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
	@Autowired
	DepartmentRegistService departmentRegistService;
	@Autowired
	DepartmentListService departmentListService;
	@Autowired
	DepartmentDetailService departmentDetailService;
	@Autowired
	DepartmentUpdateService departmentUpdateService;
	@Autowired
	EvalRegistService evalRegistService;
	@Autowired
	EvalInfoService evalInfoService;
	@Autowired
	EvalUpdateService evalUpdateService;
	@Autowired
	EvalDeleteService evalDeleteService;
	@Autowired
	SalarySelectService salarySelectService;
	
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
		departmentListService.execute(model);
		return "thymeleaf/department/departmentList";
	}
	@GetMapping("departmentRegist")
	public String departmentRegist() {
		return "thymeleaf/department/departmentRegist";
	}
	@PostMapping("departmentRegist")
	public String departmentRegist(DepartmentCommand departmentCommand) {
		departmentRegistService.execute(departmentCommand);
		return "redirect:departmentList";
	}
	@GetMapping("departmentUpdate")
	public String departmentUpdate(String departmentNum,Model model) {
		departmentDetailService.execute(departmentNum, model);
		return "thymeleaf/department/departmentUpdate";
	}
	@PostMapping("departmentUpdate")
	public String departmentUpdate(DepartmentCommand departmentCommand) {
		departmentUpdateService.execute(departmentCommand);
		return "redirect:departmentList";
	}
	@GetMapping("departmentItem")
	public String departmentItem(Model model, String empNum) {
		departmentListService.execute(model);
		model.addAttribute("empNum", empNum);
		return "thymeleaf/department/departmentItem";
	}
	@GetMapping("eval")
	public String eval(String empNum, Model model) {
		model.addAttribute("empNum", empNum);
		return "thymeleaf/emp/eval";
	}
	@RequestMapping("eval")
	public String eval(@Validated EvalCommand evalCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/emp/eval";
		}
		evalRegistService.execute(evalCommand);
		return "redirect:employeeList";
	}
	@GetMapping("evalInfo")
	public String evalInfo(String empNum, Model model) {
		evalInfoService.execute(empNum, model);
		return "thymeleaf/emp/evalInfo";
	}
	@GetMapping("evalUpdate")
	public String evalUpdate(String empNum, Model model) {
		evalInfoService.execute(empNum, model);
		return "thymeleaf/emp/evalUpdate";
	}
	@PostMapping("evalUpdate")
	public String evalUpdate(@Validated EvalCommand evalCommand, BindingResult result) {
			if(result.hasErrors()) {
				return "thymeleaf/emp/evalUpdate";
			}
			evalUpdateService.execute(evalCommand);
			return "redirect:evalInfo?empNum="+evalCommand.getEmpNum();
	}
	@GetMapping("evalDelete")
	public String evalDelete(String empNum) {
		evalDeleteService.execute(empNum);	
		return "redirect:employeeList";
	}
	@GetMapping("salary")
	public String salary(String empNum, Model model) {
		salarySelectService.execute(empNum, model);
		return "thymeleaf/emp/salary";
	}
	
		
		
	
	
}
