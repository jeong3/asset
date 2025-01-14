package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("salaryDTO")
@Data
public class SalaryDTO {
	String empNum;
	int salary;
	int deductions;
	int bonus;
	Date salaryDate;
}
