package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public AuthInfoDTO login(String userId);
}
