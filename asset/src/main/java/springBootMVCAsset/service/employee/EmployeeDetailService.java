package springBootMVCAsset.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.EmployeeDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class EmployeeDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String empNum, Model model) {
		EmployeeDTO dto = employeeMapper.employeeSelectOne(empNum);
		model.addAttribute("employeeCommand", dto);
		
	}
	
}
