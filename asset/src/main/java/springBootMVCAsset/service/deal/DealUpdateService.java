package springBootMVCAsset.service.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealUpdateService {
	@Autowired
	DealMapper dealMapper;
	public void execute(DealCommand dealCommand, String categoryName) {
		DealDTO dto = new DealDTO();
		dto.setCategoryName(categoryName);
		dto.setCategoryType(dealCommand.getCategoryType());
		dto.setDealContents(dealCommand.getDealContents());
		dto.setDealDate(dealCommand.getDealDate());
		dto.setDealMethod(dealCommand.getDealMethod());
		dto.setDealNum(dealCommand.getDealNum());
		dto.setDealPrice(dealCommand.getDealPrice());
		
		dealMapper.dealUpdate(dto);
	}
}
