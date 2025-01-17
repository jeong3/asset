package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.AnnounceDTO;

@Mapper
public interface AnnounceMapper {
	public void announceInsert(AnnounceDTO dto);
	public List<AnnounceDTO> announceSelectAll();
	public AnnounceDTO announceSelectOne(String announceNum);
	public void announceUpdate(AnnounceDTO dto);
	public void announceDelete(String announceNum);
}
