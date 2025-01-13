package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VolunteerCommand {
	String receiptNum;
	String volunteerId;
	String announceNum;
	String volunteerPw;
	String volunteerName;
	String volunteerEngname;
	String volunteerPhone;
	String volunteerEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date volunteerBirth;
}
