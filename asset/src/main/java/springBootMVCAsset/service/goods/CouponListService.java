package springBootMVCAsset.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.CouponDTO;
import springBootMVCAsset.mapper.CouponMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class CouponListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	CouponMapper couponMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		
		List<CouponDTO> list = couponMapper.couponSelect(memberNum);
		model.addAttribute("list", list);
		
	}
}
