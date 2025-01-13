package springBootMVCAsset.service.announce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.AnnounceDTO;
import springBootMVCAsset.mapper.AnnounceMapper;

@Service
public class AnnounceListService {
	@Autowired
	AnnounceMapper announceMapper;
	public void execute(Model model) {
		List<AnnounceDTO> list = announceMapper.announceSelectAll();
		model.addAttribute("list", list);
		System.out.println("list :" + list);
	}
}
