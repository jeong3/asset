package springBootMVCAsset.service.news;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.NewsCommentCommend;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.NewsCommentDTO;
import springBootMVCAsset.mapper.NewsMapper;

@Service
public class CommentRegistService {
	@Autowired
	NewsMapper newsMapper;
	public void execute(NewsCommentCommend newsCommentCommend, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberId = auth.getUserId();
		NewsCommentDTO dto = new NewsCommentDTO();
		BeanUtils.copyProperties(newsCommentCommend, dto);
		dto.setMemberId(memberId);
		newsMapper.commentInsert(dto);
			
		
		
	}

}
