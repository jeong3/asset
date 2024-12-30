package springBootMVCAsset.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.mapper.CartMapper;

@Service
public class CartUpdateService {

    @Autowired
    CartMapper cartMapper;

    // 수량을 1만 감소시키는 서비스 메소드
    public String execute(String goodsNum, String memberNum) {
        int result = cartMapper.qtyDown(goodsNum, memberNum);  // 수량을 1 감소
        if (result > 0) {
            return "success";
        } else {
            return "failure";
        }
    }
}
