package springBootMVCAsset.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.CartDTO;
import springBootMVCAsset.domain.GoodsCartDTO;

@Mapper
public interface CartMapper {
	public void cartMerge(CartDTO dto);
	public List<GoodsCartDTO> cartSelectList(
			@Param("memberNum") String memberNum, 
			@Param("nums") String [] nums);
	public int goodsNumsDelete(Map<String, Object> condition);
	public int cartQtyDown(@Param("goodsNum") String goodsNum
            ,@Param("memberNum") String memberNum);
}
