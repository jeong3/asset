package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.DeliveryDTO;

@Mapper
public interface DeliveryMapper {
	public Integer deliveryInsert(DeliveryDTO dto);
	public Integer deliveryStatusUpdate(String purchaseNum);
	public Integer deliveryDelete(String purchaseNum);
}	
