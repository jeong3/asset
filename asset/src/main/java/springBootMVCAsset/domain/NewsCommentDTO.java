package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("newsCommentDTO")
@Data
public class NewsCommentDTO {
	String newsNum;
	String commentNum;
	String memberId;
	Date commentDate;
	String commentContents;
	
}
