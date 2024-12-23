package springBootMVCAsset.mapper;

import java.util.List;

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
}
