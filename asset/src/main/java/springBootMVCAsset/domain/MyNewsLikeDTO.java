package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("myNewsLikeDTO")
@Data
public class MyNewsLikeDTO {
	NewsDTO newsDTO;
	String newsNum;
	String memberId;
	Date recommendDate;
}
