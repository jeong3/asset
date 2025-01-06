package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.domain.SearchDTO;

@Mapper
public interface DealMapper {
	public void dealInsert(DealDTO dto);
	public List <DealDTO> dealList(SearchDTO dto);
	public Integer dealCount();
	public DealDTO dealDetail(String dealNum);
	public void dealUpdate(DealDTO dto);
}
