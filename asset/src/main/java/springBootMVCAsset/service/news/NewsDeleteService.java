package springBootMVCAsset.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.NewsMapper;

@Service
public class NewsDeleteService {
	@Autowired
	NewsMapper newsMapper;
	public void execute(String newsNum) {
		newsMapper.newsDelete(newsNum);
		
	}

}
