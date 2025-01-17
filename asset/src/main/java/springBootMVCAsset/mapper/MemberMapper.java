package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public void memberinsert(MemberDTO dto);
	public List <MemberDTO> memberList();
	public MemberDTO memberDetail(String memberNum);
	public void memberUpdate(MemberDTO dto);
	public void memberDelete(String memberNum);
	public String memberNumSelect(String memberId);
	public AuthInfoDTO findByKakaoId(String memberId);
	public void KakaoAuthSave(AuthInfoDTO newAuth);
	public String memberNumSelectToPurchase(String purchaseNum);
	public AuthInfoDTO memberSelect(String memberNum);
	public int pwCheck(String userId, String userPhone);
}
