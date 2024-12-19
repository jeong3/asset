package springBootMVCAsset.service.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.EmployeeCommand;
import springBootMVCAsset.domain.EmployeeDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class EmployeeRegistService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employeeCommand, dto);
		String encodePw = passwordEncoder.encode(employeeCommand.getEmpPw());
		dto.setEmpPw(encodePw);
		employeeMapper.employeeInsert(dto);
		
		
	}

}

	