package springBootMVCAsset.service.deal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.domain.SearchDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealListService {
	@Autowired
	DealMapper dealMapper;
	@Autowired
	DealSearchService dealSearchService;
	public void execute(Integer page, SearchDTO searchDTO, Model model, HttpSession session) {
		
		Integer limit = 5;
		
		// 필터링 값 가져옴
		String categoryName = searchDTO.getCategoryName();
		String categoryType = searchDTO.getCategoryType();
		String dealMethod = searchDTO.getDealMethod();
		Date dealStartDate = searchDTO.getDealStartDate();
		Date dealEndDate = searchDTO.getDealEndDate();
		String searchWord = searchDTO.getSearchWord();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		
		SearchDTO dto = dealSearchService.execute(page, limit, categoryName, categoryType, dealMethod, dealStartDate, dealEndDate, searchWord, memberNum);
		
		List <DealDTO> list = dealMapper.dealList(dto);
		
		Integer count = dealMapper.dealCount();
		
		dealSearchService.execute(page, limit, count, searchWord, model);
		model.addAttribute("list", list);
		
		// 필터링 값 null일 때
		if(searchWord == null) searchWord = "";
		if(categoryName ==  null || categoryName == "전체") categoryName = "전체";
		if(categoryType == null || categoryType == "전체") categoryType = "전체";
		if(dealMethod == null || dealMethod == "전체") dealMethod = "전체";
		if(dealStartDate == null) {
			 Calendar calendar = Calendar.getInstance();
			    calendar.set(Calendar.DAY_OF_MONTH, 1); // 이번 달 1일
			    dealStartDate = calendar.getTime();
		}
		if(dealEndDate == null) { // 이번 달 말일
			LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		    dealEndDate = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		dto.setCategoryName(categoryName);
		dto.setCategoryType(categoryType);
		dto.setDealEndDate(dealEndDate);
		dto.setDealStartDate(dealStartDate);
		dto.setDealMethod(dealMethod);
		dto.setSearchWord(searchWord);
		model.addAttribute("searchDTO", dto);
		
		// 멤버 아이디
		String memberId = auth.getUserId();
		model.addAttribute("memberId", memberId);
	}
}
