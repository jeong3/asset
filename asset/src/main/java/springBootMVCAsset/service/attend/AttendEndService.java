package springBootMVCAsset.service.attend;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.AttendDTO;
import springBootMVCAsset.mapper.AttendMapper;

@Service
public class AttendEndService {
	@Autowired
	AttendMapper attendMapper;
	public void execute(String empNum, String attendNum) {
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		String targetTime = "17:50";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String startTimeStr = sdf.format(endTime);
        String absenceWhether = "N";
        if (startTimeStr.compareTo(targetTime) > 0) {
        	absenceWhether = "조퇴";  // 조퇴
        } 
       
		AttendDTO dto = new AttendDTO();
		dto.setAttendNum(attendNum);
		dto.setEmpNum(empNum);
		dto.setAbsenceWhether(absenceWhether);
		dto.setEndTime(endTime);
		attendMapper.AttendUpdate(dto);
		
	}
}
