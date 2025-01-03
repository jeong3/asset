package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date dealStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date dealEndDate;
	String searchWord;
	String memberNum;
	
	public SearchDTO() {
	}
	
}
