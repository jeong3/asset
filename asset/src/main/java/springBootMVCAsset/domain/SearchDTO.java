package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Alias("searchDTO")
@Data
@AllArgsConstructor
public class SearchDTO {
	int startRow;
	int endRow;
	String categoryName;
	String categoryType;
	String dealMethod;
	Date dealStartDate;
	Date dealEndDate;
	String searchWord;
	String memberNum;
	
	public SearchDTO() {
	}
	
}
