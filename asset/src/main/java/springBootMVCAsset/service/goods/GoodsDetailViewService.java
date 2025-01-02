package springBootMVCAsset.service.goods;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.GoodsStockDTO;
import springBootMVCAsset.mapper.GoodsStockMapper;
import springBootMVCAsset.mapper.MemberMapper;

@Service
public class GoodsDetailViewService {
	@Autowired
	GoodsStockMapper goodsStockMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(String goodsNum, Model model, HttpServletResponse response
			, HttpSession session) {
		GoodsStockDTO dto = goodsStockMapper.goodsStockSelectOne(goodsNum);
		model.addAttribute("dto", dto);
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(auth != null) {
			String memberNum =  memberMapper.memberNumSelect(auth.getUserId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("goodsNum", goodsNum);
			map.put("memberNum", memberNum);
			model.addAttribute("map", map);
		}
		ObjectMapper mapper = new ObjectMapper();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		try {
			response.getWriter().print(mapper.writeValueAsString(dto));
		}catch(Exception e) {}
		
	}
}
