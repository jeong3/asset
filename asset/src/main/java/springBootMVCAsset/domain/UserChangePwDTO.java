package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("userChangePwDTO")
public class UserChangePwDTO {
	String userId;
	String userPhone;
	String userPw;	

	String tableName;
	String pwColumName;
	String userIdColumName;
	String phoneColumn;
}
