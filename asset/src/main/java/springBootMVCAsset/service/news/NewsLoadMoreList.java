package springBootMVCAsset.service.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.NewsDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class NewsLoadMoreList {
	@Autowired
	NewsMapper newsMapper;
	public void execute(Integer page, String searchWord, Model model) {
		int limit = 4; // 1 ~ 6
		int startRow = ((page - 1) * limit) + 1; //1
		int endRow = startRow + limit - 1; //6
		List<NewsDTO> list = newsMapper.newsLoadMoreSelect(1,searchWord, endRow);
		Integer count = newsMapper.newsCount();
		int maxPage = (int)((double) count / limit + 0.95);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("list", list);
		
	}

}
