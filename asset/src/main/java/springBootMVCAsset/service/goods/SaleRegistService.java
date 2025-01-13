package springBootMVCAsset.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.StockMapper;

@Service
public class SaleRegistService {
	@Autowired
	StockMapper stockMapper;
	public void execute() {
		double close = stockMapper.closeSelect();
		double sale = 1;
		if(close > 57000) {
			sale = 0.2;
		} else if(close > 55000) {
			sale = 0.1;
		}
		stockMapper.saleInsert(sale);
		
	}

}