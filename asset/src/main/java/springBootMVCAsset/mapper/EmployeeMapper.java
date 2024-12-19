package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.DepartmentDTO;
import springBootMVCAsset.domain.EmployeeDTO;
@Mapper
public interface EmployeeMapper {

	int employeeInsert(EmployeeDTO dto);

	List<EmployeeDTO> employeeSelectAll();

	EmployeeDTO employeeSelectOne(String empNum);

	int employeeUpdate(EmployeeDTO dto);

	int employeeDelete(String empNum);

	int departmentInsert(DepartmentDTO dto);

	List<DepartmentDTO> departmentEmployeeSelectAll();

	DepartmentDTO departmentEmployeeSelectOne(String departmentNum);

	int departmentUpdate(DepartmentDTO dto);

}
