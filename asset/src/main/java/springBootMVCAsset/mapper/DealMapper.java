package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.DealDTO;

@Mapper
public interface DealMapper {
	public void dealInsert(DealDTO dto);
}
