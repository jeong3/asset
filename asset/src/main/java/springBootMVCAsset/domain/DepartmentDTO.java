package springBootMVCAsset.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("departmentDTO")
public class DepartmentDTO {
	String departmentNum;
	String departmentName;
	String managerNum;
	String departmentTel;
	String departmentContents;
	String jobTitle;
	List<EmployeeDTO> employeeDTO;
}
