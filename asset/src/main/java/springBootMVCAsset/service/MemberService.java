package springBootMVCAsset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.KakaoUser;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
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
           memberMapper.KakaoAuthSave(newAuth);
            return newAuth;
        }
    }
}
