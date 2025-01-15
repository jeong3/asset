package springBootMVCAsset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.MemberCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.KakaoUser;
import springBootMVCAsset.mapper.MemberMapper;
import springBootMVCAsset.service.budget.BudgetRegistService;

@Service
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	BudgetRegistService budgetRegistService;
	public AuthInfoDTO findOrCreateMember(KakaoUser kakaoUser) {
        // 카카오 ID로 사용자 검색
        AuthInfoDTO auth = memberMapper.findByKakaoId(kakaoUser.getId());

        if (auth != null) {
            // 사용자 존재 시 반환
            return auth;
        } else {
            // 사용자 미존재 시 새로운 사용자 생성
            AuthInfoDTO newAuth = new AuthInfoDTO();
            newAuth.setUserId(kakaoUser.getId());
            // 사용자 저장
      
           String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
           newAuth.setUserNum(autoNum);
           memberMapper.KakaoAuthSave(newAuth);
           String memberNum = newAuth.getUserNum();
           MemberCommand memberCommand = new MemberCommand();
           memberCommand.setMemberNum(memberNum);
           String budgetNum = autoNumService.execute("budget_", "budget_num", 8, "budget");
           budgetRegistService.execute(memberCommand, budgetNum);
           
           return newAuth;
        }
    }
}
