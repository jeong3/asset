package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class GoalCommand {
	String goalNum;
	String memberNum;
	String goalName;
	
	@NotEmpty(message = "목표 내용을 입력해주세요.")
	String goalContents;
	
	@NotNull(message = "목표 금액을 입력해주세요")
	Integer goalPrice;
	
	Integer currentPrice;
	Integer goalRate;
	
	@NotNull(message = "목표 시작일을 선택해주세요")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate;
	
	@NotNull(message = "목표 종료일을 선택해주세요")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDate;
}
