package springBootMVCAsset.service.announce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AnnounceDTO;
import springBootMVCAsset.mapper.AnnounceMapper;

@Service
public class AnnounceDetailService {
	@Autowired
	AnnounceMapper announceMapper;
	public void execute(String announceNum, Model model, HttpSession session) {
		AnnounceDTO dto = announceMapper.announceSelectOne(announceNum);
		model.addAttribute("dto", dto);
		System.out.println("공고내용 : " + dto);
	}
}
