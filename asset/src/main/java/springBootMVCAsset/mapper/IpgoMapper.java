package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.IpgoDTO;

@Mapper
public interface IpgoMapper {
	public void ipgoRegist(IpgoDTO dto);
}
