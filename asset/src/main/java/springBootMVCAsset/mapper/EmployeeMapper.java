package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import jakarta.validation.constraints.AssertFalse.List;
import springBootMVCAsset.domain.EmployeeDTO;
@Mapper
public interface EmployeeMapper {

	int employeeInsert(EmployeeDTO dto);

	List<EmployeeDTO> employeeSelectAll();

}
