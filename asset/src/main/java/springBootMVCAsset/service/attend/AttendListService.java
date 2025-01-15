package springBootMVCAsset.service.attend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.AttendDTO;
import springBootMVCAsset.mapper.AttendMapper;

@Service
public class AttendListService {
	@Autowired
	AttendMapper attendMapper;
	public List<String> getThisMonthDates() {
		
	    List<String> dates = new ArrayList<>();
	    LocalDate startDate = LocalDate.of(2025, 1, 1);  // 2025년 1월 1일
	    LocalDate endDate = LocalDate.of(2025, 1, 31);   // 2025년 1월 31일
	   
	    while (!startDate.isAfter(endDate)) {
	        System.out.println("생성된 날짜: " + startDate);
	        dates.add(startDate.toString());
	        startDate = startDate.plusDays(1);  // 하루씩 증가
	    }
	    return dates;
	}

    // 특정 직원의 이번 달 근태 기록 조회
    public Map<String, AttendDTO> getAttendRecords(String empNum) {
        List<String> dates = getThisMonthDates();
        Map<String, AttendDTO> attendRecords = new HashMap<>();

        // DB에서 출근 데이터 조회
        for (String date : dates) {
            AttendDTO attend = attendMapper.AttendSelect(empNum, date);
            System.out.println(date);
            System.out.println("어탠드"+attend);
            if (attend != null) {
                attendRecords.put(date, attend);  // 출근 기록이 있으면 Map에 추가
            } else {
                attendRecords.put(date, null);  // 결근으로 처리
            }
        }
        return attendRecords;
    }

	public int execute(String empNum) {
		int count = attendMapper.atWorkSelect(empNum);
		return count;
	}

}
