package springBootMVCAsset.service.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.NewsAnalyzeDTO;
import springBootMVCAsset.domain.NewsCommentDTO;
import springBootMVCAsset.domain.NewsDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class NewsDetailService {
	@Autowired
	NewsMapper newsMapper;
	
	public String convertNewlinesToHtml(String text) {
	    if (text != null) {
	        // \n을 <br>로 변환
	        return text.replace("\n", "<br>");
	    }
	    return text;
	}

	public void execute(String newsNum, Model model, HttpSession session) {
		
		NewsDTO dto = newsMapper.newsSelectOne(newsNum);
		newsMapper.countUpdate(newsNum);
		model.addAttribute("dto", dto);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		
		List<NewsCommentDTO> commentList = newsMapper.commentSelectAll(newsNum);
		model.addAttribute("commentList", commentList);
		
		if(auth != null) {
			String memberId = auth.getUserId();
			String recommend = newsMapper.newsAnalyzeSelect(newsNum, memberId);
			model.addAttribute("recommend", recommend);
			
			String memberNum = auth.getUserNum();
			String saveDate = newsMapper.newsSaveSelect(newsNum, memberNum);
			model.addAttribute("saveDate", saveDate);
		}

		int recommendCount = newsMapper.newsAnalyzeCount(newsNum);
		model.addAttribute("recommendCount", recommendCount);
		
		
		
		
		
		
		String newsContents = dto.getNewsContents();
		String formattedNewsContents = convertNewlinesToHtml(newsContents); // 줄 바꿈 처리
		model.addAttribute("newsContents", formattedNewsContents);
		
	}

}
