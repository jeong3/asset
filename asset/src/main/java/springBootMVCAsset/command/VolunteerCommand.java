package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VolunteerCommand {
	String receiptNum;
	@NotEmpty(message = "아이디를 입력해주세요.")
	String volunteerId;
	String announceNum;
	String volunteerPw;
	@NotBlank(message = "이름을 입력해주세요")
	String volunteerName;
	String volunteerEngname;
	String volunteerPhone;
	@NotBlank(message = "이메일을 입력해주세요")
	String volunteerEmail;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date volunteerBirth;
}
