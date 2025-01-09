package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AnnounceCommand {
	String announceNum;
	String announceName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date announceDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDate;
	String announceContents;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date resultDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date interviewDate;
	String interviewAddr;
	String departmentNum;
}
