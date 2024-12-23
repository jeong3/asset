package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public AuthInfoDTO login(String userId);
	public Integer userIdCheck(@Param("userId") String userId);
}
