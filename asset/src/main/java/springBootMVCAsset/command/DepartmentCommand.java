package springBootMVCAsset.command;

import lombok.Data;

@Data
public class DepartmentCommand {
	String departmentNum;
	String departmentName;
	String managerNum;
	String departmentTel;
	String departmentContents;
	String jobTitle;
}
