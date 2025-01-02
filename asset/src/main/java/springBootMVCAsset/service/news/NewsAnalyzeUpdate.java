package springBootMVCAsset.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class NewsAnalyzeUpdate {
	@Autowired
	NewsMapper newsMapper;
	public String execute(String newsNum, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberId = auth.getUserId();
		
		newsMapper.updateLikeCount(newsNum, memberId);
		String recommendDate = newsMapper.newsAnalyzeSelect(newsNum, memberId);
		model.addAttribute("recommendDate", recommendDate);
		return recommendDate;

	}

}
