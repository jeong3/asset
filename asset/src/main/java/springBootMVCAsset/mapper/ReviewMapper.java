package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {
	public ReviewDTO reviewSelectOne(ReviewDTO dto);
	public int reviewInsert(ReviewDTO dto);
	public int reviewUpdate(ReviewDTO dto);
}
