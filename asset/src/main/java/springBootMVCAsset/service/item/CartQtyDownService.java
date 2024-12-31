package springBootMVCAsset.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.mapper.CartMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class CartQtyDownService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	CartMapper cartMapper;
	public void execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		cartMapper.cartQtyDown(goodsNum, memberNum);
	}
}
