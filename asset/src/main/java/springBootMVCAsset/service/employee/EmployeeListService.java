package springBootMVCAsset.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.validation.constraints.AssertFalse.List;
import springBootMVCAsset.domain.EmployeeDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(Model model) {
		List<EmployeeDTO> list = employeeMapper.employeeSelectAll();
		
	}

}
