package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("dealDTO")
@Data
public class DealDTO {
	String dealNum;
	String memberNum;
	Integer dealPrice;
	String dealContents;
	String dealMethod;
	Date dealDate;
	String categoryName;
	String categoryType;
}
