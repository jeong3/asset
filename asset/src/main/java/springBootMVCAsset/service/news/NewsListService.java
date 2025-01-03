package springBootMVCAsset.service.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.domain.NewsDTO;
import springBootMVCAsset.domain.StartEndPageDTO;
import springBootMVCAsset.mapper.NewsMapper;
import springBootMVCAsset.service.StartEndPageService;

@Service
public class NewsListService {
	@Autowired
	NewsMapper newsMapper;
	@Autowired
	StartEndPageService startEndPageService;
	public void execute(Integer page, String searchWord, Model model) {
		Integer limit = 4;
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, searchWord, limit);
		
		List<NewsDTO> list = newsMapper.newsSelectAll(sepDTO);
		model.addAttribute("list", list);
		Integer count = newsMapper.newsCount();
		startEndPageService.execute(page, limit, count, searchWord, list, model);
	}

}
