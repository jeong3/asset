package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("evalEmployeeDTO")
@Data
public class EvalEmployeeDTO {
	EvalDTO evalDTO;
	EmployeeDTO employeeDTO;
}
