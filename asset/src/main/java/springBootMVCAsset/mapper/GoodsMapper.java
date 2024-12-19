package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.GoodsDTO;

@Mapper
public interface GoodsMapper {
	public void goodsInsert(GoodsDTO dto);
	public List<GoodsDTO> goodsAllSelect(String goodsKind);
}
