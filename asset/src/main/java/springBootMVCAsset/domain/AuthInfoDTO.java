package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("authDTO")
@Data
public class AuthInfoDTO {
	String userNum;
	String userId;
	String userPw;
	String userName;
	String userEmail;
	String grade;
}
