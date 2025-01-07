package springBootMVCAsset.service.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.domain.StockDataDTO;
import springBootMVCAsset.mapper.StockMapper;

@Service
public class StockListService {
	@Autowired
	StockMapper stockMapper;
	public List<StockDataDTO> execute() {
		List<StockDataDTO> list =  stockMapper.stockDataSelect();
		System.out.println("stockData"+list);
		return list;
	}

}
