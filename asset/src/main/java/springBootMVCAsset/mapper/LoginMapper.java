package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	AuthInfoDTO LoginSelectOne(String userId);

	Integer idCheckSelectOne(String userId);
	
}
