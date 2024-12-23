package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.MemberDTO;

@Mapper
public interface MyPageMapper {
	public MemberDTO memberMyDetail(String memberId);
	public void memberMyUpdate(MemberDTO dto);
}
