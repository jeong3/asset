package springBootMVCAsset.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.AutoNumMapper;

@Service
public class GoalsDeleteService {
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(String goalNums[]) {
		autoNumMapper.numsDelete(goalNums, "goal", "goal_num");
	}
}
