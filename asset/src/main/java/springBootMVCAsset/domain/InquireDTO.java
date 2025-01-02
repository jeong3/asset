package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inquire")
public class InquireDTO {
	   String inquireNum;
	   String memberNum;
	   String goodsNum;
	   String inquireKind;
	   String inquireSubject;
	   String inquireContents;
	   Date inquireDate;
	   String inquireAnswer; // 답변
	   Date inquireAnswerDate;
	   String empNum;
	   
	   String memberId;
	   String goodsName;
}
