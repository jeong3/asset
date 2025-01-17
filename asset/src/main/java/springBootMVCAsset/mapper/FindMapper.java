package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.UserChangePwDTO;

@Mapper
public interface FindMapper {
	public String findId(@Param("_userPhone")String userPhone
			   , @Param("_userEmail")String userEmail);
	public int pwUpdate(UserChangePwDTO dto);
	public String compare(String userPhone, String userId);
}
