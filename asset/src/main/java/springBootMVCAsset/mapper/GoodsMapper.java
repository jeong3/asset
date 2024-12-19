package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.GoodsDTO;

@Mapper
public interface GoodsMapper {
	public void goodsInsert(GoodsDTO dto);
}
