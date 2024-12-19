package springBootMVCAsset.service.department;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.DepartmentCommand;
import springBootMVCAsset.domain.DepartmentDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class DepartmentUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(DepartmentCommand departmentCommand) {
		DepartmentDTO dto = new DepartmentDTO();
		BeanUtils.copyProperties(departmentCommand, dto);
		employeeMapper.departmentUpdate(dto);
		
	}

}
