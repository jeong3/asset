package springBootMVCAsset.service.item;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.GoodsCartDTO;
import springBootMVCAsset.mapper.CartMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class CartListService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    CartMapper cartMapper;

    public void execute(Model model, HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = memberMapper.memberNumSelect(auth.getUserId());
        
        List<GoodsCartDTO> list = cartMapper.cartSelectList(memberNum, null);
        model.addAttribute("list", list);
        
        BigDecimal totPri = BigDecimal.ZERO;  // 총 금액을 BigDecimal로 초기화
        int totQty = 0;  // 수량은 Integer로 그대로 사용
        
        for (GoodsCartDTO dto : list) {
            // null 체크 추가
            if (dto.getGoodsDTO() != null && dto.getCartDTO() != null) {
                // 상품 가격을 BigDecimal로 변환
                BigDecimal goodsPrice = BigDecimal.valueOf(dto.getGoodsDTO().getGoodsPrice());
                BigDecimal sale = dto.getGoodsDTO().getSale();  // 할인율은 BigDecimal로 가정
                int cartQty = dto.getCartDTO().getCartQty();  // 수량은 그대로 사용
                
                // 할인된 가격 계산
                BigDecimal itemPrice = goodsPrice.multiply(BigDecimal.ONE.subtract(sale));
                
                // 총 금액 계산
                BigDecimal itemTotalPrice = itemPrice.multiply(BigDecimal.valueOf(cartQty));
                totPri = totPri.add(itemTotalPrice);  // 총 금액에 더함
                totQty += cartQty;  // 총 수량에 더함
            }
        }

        model.addAttribute("totPri", totPri);
        model.addAttribute("totQty", totQty);
    }
}
