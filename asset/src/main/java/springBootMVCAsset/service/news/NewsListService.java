package springBootMVCAsset.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

import springBootMVCAsset.domain.NewsDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class NewsListService {
	@Autowired
	NewsMapper newsMapper;
	public void execute(Model model) {
		List<NewsDTO> list = newsMapper.newsSelectAll();
		model.addAttribute("list", list);
	}

}
