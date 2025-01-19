package springBootMVCAsset.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.GoalMapper;

@Service
public class GoalPriceUpdateService {
	@Autowired
	GoalMapper goalMapper;
	public void execute(HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		goalMapper.goalPriceUpdate1(memberNum);
		goalMapper.goalPriceUpdate2(memberNum);
	}
}
