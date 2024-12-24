package springBootMVCAsset.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

	public void execute(String newsNum, Model model) {
		NewsDTO dto = newsMapper.newsSelectOne(newsNum);
		model.addAttribute("dto", dto);
		 String newsContents = dto.getNewsContents();
		 String formattedNewsContents = convertNewlinesToHtml(newsContents); // 줄 바꿈 처리
		 model.addAttribute("newsContents", formattedNewsContents);
		
	}

}
