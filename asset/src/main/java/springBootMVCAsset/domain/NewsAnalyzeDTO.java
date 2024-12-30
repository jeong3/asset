package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("newsAnalyzeDTO")
@Data
public class NewsAnalyzeDTO {

	String newsNum;
	int visitCount;
	Date visitDate;
	int recommend;
	Date recommendDate;
	String memberId;
	
}
