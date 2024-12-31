package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.ReviewDTO;
import springBootMVCAsset.mapper.MemberMapper;
import springBootMVCAsset.mapper.ReviewMapper;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	ReviewMapper reviewMapper;
	@RequestMapping(value="goodsReview" , method=RequestMethod.GET)
	public String goodsReview(
			 String goodsNum
			,String purchaseNum
			,Model model) {
		ReviewDTO dto = new ReviewDTO();
		dto.setGoodsNum(goodsNum);
		dto.setPurchaseNum(purchaseNum);
		
		ReviewDTO review =  reviewMapper.reviewSelectOne(dto);
		if (review != null) {
	        dto.setReviewSubject(review.getReviewSubject());
	        dto.setReviewContents(review.getReviewContents());
	    }
		model.addAttribute("dto", dto);
		return "thymeleaf/reviews/goodsReview";
	}
	@Autowired
	MemberMapper memberMapper; 

	@RequestMapping("reviewWrite")
	public String reviewWrite(ReviewDTO dto, HttpSession session) {
	    // 현재 리뷰가 존재하는지 확인
	    ReviewDTO review = reviewMapper.reviewSelectOne(dto);
	    
	    // 로그인된 사용자 정보 가져오기
	    AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
	    String memberId = auth.getUserId();
	    dto.setMemberId(memberId);

	    // 리뷰가 없으면 새로 작성, 있으면 업데이트
	    if (review == null) {
	        reviewMapper.reviewInsert(dto);
	    } else {
	        reviewMapper.reviewUpdate(dto);
	    }

	    return "redirect:/purchase/orderList";
	}
}
