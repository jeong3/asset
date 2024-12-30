package springBootMVCAsset.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.command.CartCommand;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.CartDTO;
import springBootMVCAsset.mapper.CartMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class CartInsertService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	CartMapper cartMapper;
	public String execute(CartCommand cartCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = null;
		CartDTO dto = new CartDTO();
		try {
			memberNum = memberMapper.memberNumSelect(auth.getUserId());
		}catch(Exception e) {
			e.printStackTrace();
			return "000";  // session이 없다
		}
		if(memberNum == null) {
			System.out.println("dsds");
			return "900";
			
		}else {
			dto.setCartQty(cartCommand.getCartQty());
			dto.setGoodsNum(cartCommand.getGoodsNum());
			dto.setMemberNum(memberNum);
			System.out.println("Cartdto1 : " + dto);
			cartMapper.cartMerge(dto);
			return "200";
		}
		
	}
}
