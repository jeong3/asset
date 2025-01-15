package springBootMVCAsset.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.GoalCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.GoalDTO;
import springBootMVCAsset.mapper.GoalMapper;

@Service
public class GoalUpdateService {
	@Autowired
	GoalMapper goalMapper;
	
	public void execute(GoalCommand goalCommand, HttpSession session) {
		GoalDTO dto = new GoalDTO();
		dto.setEndDate(goalCommand.getEndDate());
		dto.setGoalContents(goalCommand.getGoalContents());
		dto.setGoalName(goalCommand.getGoalName());
		dto.setGoalNum(goalCommand.getGoalNum());
		dto.setGoalPrice(goalCommand.getGoalPrice());
		dto.setStartDate(goalCommand.getStartDate());
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		dto.setMemberNum(memberNum);
		
		if(dto.getGoalName().equals("저축")){
			goalMapper.goalUpdate1(dto);
		}else{
			goalMapper.goalUpdate2(dto);
		}
	}
}
