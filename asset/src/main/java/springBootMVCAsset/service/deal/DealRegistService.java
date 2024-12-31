package springBootMVCAsset.service.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.DealCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealRegistService {
	@Autowired
	DealMapper dealMapper;
	public void execute(DealCommand dealCommand, HttpSession session, String categoryName) {
		DealDTO dto = new DealDTO();
		dto.setCategoryName(categoryName);
		dto.setCategoryType(dealCommand.getCategoryType());
		dto.setDealContents(dealCommand.getDealContents());
		dto.setDealDate(dealCommand.getDealDate());
		dto.setDealMethod(dealCommand.getDealMethod());
		dto.setDealNum(dealCommand.getDealNum());
		dto.setDealPrice(dealCommand.getDealPrice());
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		dto.setMemberNum(memberNum);
		dealMapper.dealInsert(dto);
		
	}
}
