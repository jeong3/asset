package springBootMVCAsset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootMVCAsset.domain.AttendDTO;
import springBootMVCAsset.service.attend.AttendListService;

@Controller
@RequestMapping("attend")
public class AttendController {
	@Autowired
	AttendListService attendListService;
	@GetMapping("attend")
	public String attend(String empNum, Model model) {
		int count = attendListService.execute(empNum);
		model.addAttribute("count", count);
		 // 직원의 이번 달 근태 기록 조회
        Map<String, AttendDTO> attendRecords = attendListService.getAttendRecords(empNum);
        model.addAttribute("attendRecords", attendRecords);
        
        List<String> dates = attendListService.getThisMonthDates();
        
        model.addAttribute("dates", dates);
        
		model.addAttribute("empNum", empNum);
		return "thymeleaf/attend/attend";
	}
}
