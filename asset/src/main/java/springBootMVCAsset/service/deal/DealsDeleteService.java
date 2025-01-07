package springBootMVCAsset.service.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.AutoNumMapper;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealsDeleteService {
	@Autowired 
	AutoNumMapper autoNumMapper;
	public void execute(String dealNums[]) {
		autoNumMapper.numsDelete(dealNums, "deal", "deal_num");
	}
}
