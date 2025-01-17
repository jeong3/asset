package springBootMVCAsset.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.AuthInfoDTO;

@Mapper
public interface FindMapper {
	public String findId(@Param("_userPhone")String userPhone
			   , @Param("_userEmail")String userEmail);
	public AuthInfoDTO compare(Map<String, String> params);
	public void pwMemberChange(String pw, String userNum);
	public void pwEmployeeChange(String pw, String userNum);
}
