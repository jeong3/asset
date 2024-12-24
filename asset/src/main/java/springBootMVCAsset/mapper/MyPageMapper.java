package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.EmployeeDTO;
import springBootMVCAsset.domain.MemberDTO;

@Mapper
public interface MyPageMapper {
	public MemberDTO memberMyDetail(String memberId);
	
	public Integer memberMyUpdate(MemberDTO dto);
	
	public Integer memberMyPwUpdate(@Param("_newPw") String newPw
			, @Param("_memberId") String memberId);
	
	public Integer memberMyDelete(String memberId);
	
	public EmployeeDTO EmpMyDetail(String empId);

	public Integer empMyPwUpdate(String empPw, String empId);
}
