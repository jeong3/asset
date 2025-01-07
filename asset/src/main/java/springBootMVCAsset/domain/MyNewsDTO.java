package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("myNewsDTO")
@Data
public class MyNewsDTO {
	NewsDTO newsDTO;
	String newsNum;
	String memberNum;
	Date saveDate;
}
