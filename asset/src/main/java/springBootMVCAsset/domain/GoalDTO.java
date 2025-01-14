package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("goalDTO")
@Data
public class GoalDTO {
	String goalNum;
	String memberNum;
	String goalName;
	String goalContents;
	Integer goalPrice;
	Integer currentPrice;
	Integer goalRate;
	Date startDate;
	Date endDate;
	Integer myGoalNum;
	Integer goalRunCount;
}
