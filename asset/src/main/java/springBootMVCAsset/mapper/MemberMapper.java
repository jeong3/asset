package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public void memberinsert(MemberDTO dto);
	public List <MemberDTO> memberList();
	public MemberDTO memberDetail(String memberNum);
}
