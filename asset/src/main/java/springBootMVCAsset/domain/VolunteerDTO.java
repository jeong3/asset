package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("volunteerDTO")
public class VolunteerDTO {
	String receiptNum;
	String announceNum;
	String volunteerId;
	String volunteerPw;
	String volunteerName;
	String volunteerEngname;
	String volunteerPhone;
	String volunteerEmail;
	Date volunteerBirth;
	String documentResult;
	String interviewResult;
}
