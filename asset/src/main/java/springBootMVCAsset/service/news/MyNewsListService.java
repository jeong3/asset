package springBootMVCAsset.service.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.MyNewsDTO;
import springBootMVCAsset.domain.MyNewsLikeDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class MyNewsListService {
	@Autowired
	NewsMapper newsMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = auth.getUserNum();
		List<MyNewsDTO> list = newsMapper.myNewsSelect(memberNum);
		model.addAttribute("list", list);
		List<MyNewsLikeDTO> likeList = newsMapper.myNewsSelect_Like(auth.getUserId());
		model.addAttribute("likeList", likeList);
	}

}
