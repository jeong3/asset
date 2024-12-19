package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.EmployeeDTO;
@Mapper
public interface EmployeeMapper {

	int employeeInsert(EmployeeDTO dto);

	List<EmployeeDTO> employeeSelectAll();

	EmployeeDTO employeeSelectOne(String empNum);

	int employeeUpdate(EmployeeDTO dto);

	int employeeDelete(String empNum);

}
