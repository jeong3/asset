package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {
	public ReviewDTO reviewSelectOne(ReviewDTO dto);
	public int reviewInsert(ReviewDTO dto);
	public int reviewUpdate(ReviewDTO dto);
	public List<ReviewDTO> goodsReviewList(String goodsNum);
}
