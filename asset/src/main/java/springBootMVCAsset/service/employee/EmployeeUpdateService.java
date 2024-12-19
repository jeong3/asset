package springBootMVCAsset.service.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.EmployeeCommand;
import springBootMVCAsset.domain.EmployeeDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employeeCommand, dto);
		System.out.println("디티오"+dto);
		employeeMapper.employeeUpdate(dto);
		
	}

}
