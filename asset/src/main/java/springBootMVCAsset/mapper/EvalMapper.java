package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.EmpSalaryDTO;
import springBootMVCAsset.domain.EvalDTO;
import springBootMVCAsset.domain.EvalEmployeeDTO;
import springBootMVCAsset.domain.SalaryDTO;

@Mapper
public interface EvalMapper {

	int EvalInsert(EvalDTO dto);

	EvalEmployeeDTO EvalSelectOne(String empNum);

	int EvalUpdate(EvalDTO dto);

	int EvalDelete(String empNum);

	int checkCount(String empNum);

	int salaryInsert(String empNum);

	EmpSalaryDTO salarySelectOne(String empNum);

	int moneyCheck(SalaryDTO dto);
	
}
