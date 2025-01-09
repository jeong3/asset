package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("announceDTO")
public class AnnounceDTO {
	String announceNum;
	String announceName;
	Date announceDate;
	Date startDate;
	Date endDate;
	String announceContents;
	Date resultDate;
	Date interviewDate;
	String interviewAddr;
	String departmentNum;
}
