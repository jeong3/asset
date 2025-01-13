package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("empSalaryDTO")
@Data
public class EmpSalaryDTO {
	String empNum;
	String empName;
	String departmentNum;
	int salary;
	Date salaryDate;
	int attendCount;
	int workPerformanceAbility;
	int workAttitude;
	int attendStatus;
}
