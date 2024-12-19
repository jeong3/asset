package springBootMVCAsset.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

import springBootMVCAsset.domain.DepartmentDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class DepartmentListService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(Model model) {
		List<DepartmentDTO> list = employeeMapper.departmentEmployeeSelectAll();
		model.addAttribute("list", list);
		
	}

}
