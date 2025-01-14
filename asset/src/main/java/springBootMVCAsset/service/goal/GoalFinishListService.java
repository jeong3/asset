package springBootMVCAsset.service.goal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.GoalDTO;
import springBootMVCAsset.mapper.GoalMapper;

@Service
public class GoalFinishListService {
	@Autowired
	GoalMapper goalMapper;
	
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		
		List <GoalDTO> list = goalMapper.goalFinishList(memberNum);
		model.addAttribute("finishlist", list);
		
		// 멤버 아이디
		String memberId = auth.getUserId();
		model.addAttribute("memberId", memberId);
		
		Integer goalFinishCount = goalMapper.goalFinishCount(memberNum);
		model.addAttribute("goalFinishCount", goalFinishCount);
	}
}
