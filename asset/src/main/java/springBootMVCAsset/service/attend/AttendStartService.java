package springBootMVCAsset.service.attend;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.AttendDTO;
import springBootMVCAsset.mapper.AttendMapper;

@Service
public class AttendStartService {
	@Autowired
	AttendMapper attendMapper;
	public void execute(String empNum) {
		Timestamp startTime = new Timestamp(System.currentTimeMillis());
		String targetTime = "09:10";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String startTimeStr = sdf.format(startTime);
        String rateWhether = "N";
        if (startTimeStr.compareTo(targetTime) > 0) {
        	rateWhether = "Y";  // 지각
        } else {
        	rateWhether = "N";  // 지각 아님
        }
        String absenceWhether = "N";
		AttendDTO dto = new AttendDTO();
		dto.setEmpNum(empNum);
		dto.setRateWhether(rateWhether);
		dto.setAbsenceWhether(absenceWhether);
		dto.setStartTime(startTime);
		attendMapper.AttendInsert(dto);
		
		
		
	}

}
