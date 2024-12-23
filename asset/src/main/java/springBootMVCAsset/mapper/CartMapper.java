package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.CartDTO;

@Mapper
public interface CartMapper {
	public void cartMerge(CartDTO dto);
}
