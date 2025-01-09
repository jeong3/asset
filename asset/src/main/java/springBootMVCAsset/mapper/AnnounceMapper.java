package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.AnnounceDTO;

@Mapper
public interface AnnounceMapper {
	public void announceInsert(AnnounceDTO dto);
}
