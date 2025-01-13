package springBootMVCAsset.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EvalCommand {
	String evalNum;
	String empNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDate;
	@NotNull(message = "평가 점수를 입력해주세요")
	int workPerformanceAbility;
	@NotNull(message = "평가 점수를 입력해주세요")
	int workAttitude;
	@NotNull(message = "평가 점수를 입력해주세요")
	int attendStatus;
	String generalOpinion;

}
