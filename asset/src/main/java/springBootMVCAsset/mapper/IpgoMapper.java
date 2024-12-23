package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.IpgoDTO;

@Mapper
public interface IpgoMapper {
	public void ipgoRegist(IpgoDTO dto);
	public List<IpgoDTO> ipgoSelectAll();
	public void ipgoDelete(String ipgoNum);
}
