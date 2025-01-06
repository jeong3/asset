package springBootMVCAsset.service.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealDeleteService {
	@Autowired 
	DealMapper dealMapper;
	public void execute(String dealNum) {
		//dealMapper.dealDelete(dealNum);
	}
}
