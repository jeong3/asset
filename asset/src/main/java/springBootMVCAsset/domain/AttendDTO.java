package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("attendDTO")
@Data
public class AttendDTO {
	String attendNum;
	String empNum;
	Date startTime;
	Date endTime;
	String rateWhether;
	String absenceWhether;
	Date attendDate;
	
}
