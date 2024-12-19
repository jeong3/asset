package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public void memberinsert(MemberDTO dto);
}
