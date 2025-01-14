package springBootMVCAsset.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springBootMVCAsset.domain.KakaoUser;

@Service
public class KakaoService {
    public KakaoUser getKakaoUserInfo(String accessToken) {
        // HTTP 요청을 사용하여 카카오 API에서 사용자 정보 가져오기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUser> response = restTemplate.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            entity,
            KakaoUser.class
        );
        System.out.println("카카오 사용자 정보: " + response.getBody());
        return response.getBody();
    }
}
