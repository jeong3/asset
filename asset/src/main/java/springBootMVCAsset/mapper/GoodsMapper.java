package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.GoodsDTO;
import springBootMVCAsset.domain.StartEndPageDTO;

@Mapper
public interface GoodsMapper {
	public int goodsInsert(GoodsDTO dto);
	public List<GoodsDTO> goodsAllSelect(String goodsKind);
	public List<GoodsDTO> goodsSelectAll();
	public GoodsDTO goodsSelectOne(String goodsNum);
	public int goodsUpdate(GoodsDTO dto);
	public int goodsDelete(String goodsNum);
	public List<GoodsDTO> goodsLoadMoreSelect(int startRow, int endRow);
	public int goodsCount();
	public List<GoodsDTO> allSelect(StartEndPageDTO sepDTO);
}
