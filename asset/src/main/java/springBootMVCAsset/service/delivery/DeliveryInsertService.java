package springBootMVCAsset.service.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.DeliveryDTO;
import springBootMVCAsset.mapper.DeliveryMapper;

@Service
public class DeliveryInsertService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public void execute(String purchaseNum, String deliveryNum) {
		DeliveryDTO dto= new DeliveryDTO();
		dto.setDeliveryNum(deliveryNum);
		dto.setPurchaseNum(purchaseNum);
		deliveryMapper.deliveryInsert(dto);	
	}
}
