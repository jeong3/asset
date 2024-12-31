package springBootMVCAsset.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.OrderListDTO;
import springBootMVCAsset.domain.PaymentDTO;
import springBootMVCAsset.domain.PurchaseDTO;

@Mapper
public interface PurchaseMapper {
	public String selectPurchaseNum();
	public int purchaseInsert(PurchaseDTO dto);
	public int purchaseListInsert(Map<String, Object> map);
	public PurchaseDTO purchaseSelectOne(String purchaseNum);
	public int paymentInsert(PaymentDTO dto);
	public List<OrderListDTO> orderList(Map<String , String> map);
	public int paymentStatusUpdate(String purchaseNum);
	public int paymentDel(String purchaseNum);
}
