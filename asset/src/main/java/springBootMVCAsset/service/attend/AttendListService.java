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
	    LocalDate startDate = LocalDate.now().withDayOfMonth(1);
	    LocalDate endDate = LocalDate.now();  // 현재 날짜

	    while (!endDate.isBefore(startDate)) {
	        System.out.println("생성된 날짜: " + endDate);
	        dates.add(endDate.toString());
	        endDate = endDate.minusDays(1);  // 하루씩 감소
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
