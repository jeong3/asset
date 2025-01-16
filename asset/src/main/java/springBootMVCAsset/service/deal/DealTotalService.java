package springBootMVCAsset.service.deal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealTotalService {
	@Autowired
	DealMapper dealMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 이번 달 1일
		Date dealStartDate = calendar.getTime();
		 
		LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		Date dealEndDate = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 
		Integer monthEx = dealMapper.monthEx(dealStartDate, dealEndDate, memberNum);
		Integer monthIm = dealMapper.monthIm(dealStartDate, dealEndDate, memberNum);
		
		Integer todayEx = dealMapper.todayEx(memberNum);
		Integer todayIm = dealMapper.todayIm(memberNum);
		
		if(monthEx == null) {
			monthEx = 0;
		}
		if(monthIm == null) {
			monthIm = 0;
		}
		if(todayEx == null) {
			todayEx = 0;
		}
		if(todayIm == null) {
			todayIm = 0;
		}
		model.addAttribute("monthEx", monthEx);
		model.addAttribute("monthIm", monthIm);
		model.addAttribute("todayEx", todayEx);
		model.addAttribute("todayIm", todayIm);
	}
}
