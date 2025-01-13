package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("evalDTO")
@Data
public class EvalDTO {
	String evalNum;
	String empNum;
	Date startDate;
	Date endDate;
	int workPerformanceAbility;
	int workAttitude;
	int attendStatus;
	String generalOpinion;

}
