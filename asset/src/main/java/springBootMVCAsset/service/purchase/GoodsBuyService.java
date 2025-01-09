package springBootMVCAsset.service.purchase;

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
public class GoodsBuyService {
    
    @Autowired
    MemberMapper memberMapper;
    
    @Autowired
    CartMapper cartMapper;

    public void execute(String[] nums, HttpSession session, Model model) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String memberNum = memberMapper.memberNumSelect(auth.getUserId());
        List<GoodsCartDTO> list = cartMapper.cartSelectList(memberNum, nums);
        model.addAttribute("list", list);
        
        BigDecimal sumPrice = BigDecimal.ZERO;  // 결제 금액을 BigDecimal로 처리
        BigDecimal deliveryCost = BigDecimal.ONE;  // 배송비 합계금액을 BigDecimal로 처리
        String goodsNums = "";  // 상품번호들 저장
        
        for (GoodsCartDTO dto : list) {
            // 상품 가격과 할인율을 BigDecimal로 처리
            BigDecimal goodsPrice = BigDecimal.valueOf(dto.getGoodsDTO().getGoodsPrice());
            BigDecimal sale = dto.getGoodsDTO().getSale();  // 할인율을 BigDecimal로 처리
            int cartQty = dto.getCartDTO().getCartQty();  // 수량은 그대로 사용
            
            // 할인된 가격 계산
            BigDecimal itemPrice = goodsPrice.multiply(BigDecimal.ONE.subtract(sale));
            
            // 총 금액 계산
            BigDecimal itemTotalPrice = itemPrice.multiply(BigDecimal.valueOf(cartQty));
            sumPrice = sumPrice.add(itemTotalPrice);  // 결제 금액에 더함
            
            // 상품 번호 추가
            goodsNums += dto.getGoodsDTO().getGoodsNum() + "-";
        }
        
        // 결제 금액이 30,000 이상일 경우 배송비는 0
        if (sumPrice.compareTo(BigDecimal.valueOf(30000)) >= 0) {
            deliveryCost = BigDecimal.ZERO;
        }
        
        // 모델에 값 추가
        System.out.println();
        model.addAttribute("sumPrice", sumPrice);
        model.addAttribute("deliveryCost", deliveryCost);
        model.addAttribute("goodsNums", goodsNums);
    }
}
