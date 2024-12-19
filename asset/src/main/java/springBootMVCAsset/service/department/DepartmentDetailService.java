package springBootMVCAsset.service.department;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.DepartmentDTO;
import springBootMVCAsset.mapper.EmployeeMapper;

@Service
public class DepartmentDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(String departmentNum, Model model) {
		DepartmentDTO dto = employeeMapper.departmentEmployeeSelectOne(departmentNum);
		model.addAttribute("dto", dto);
	}

}
