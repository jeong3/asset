package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.EmployeeDTO;
@Mapper
public interface EmployeeMapper {

	int employeeInsert(EmployeeDTO dto);

}
