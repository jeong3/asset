package springBootMVCAsset.service.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.mapper.DealMapper;

@Service
public class DealDetailService {
	@Autowired
	DealMapper dealMapper;
	public void execute(String dealNum, Model model) {
		DealDTO dto = dealMapper.dealDetail(dealNum);
		model.addAttribute("dto", dto);
		model.addAttribute("dealCommand", dto);
	}
}
