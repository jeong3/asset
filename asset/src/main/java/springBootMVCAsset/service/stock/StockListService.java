package springBootMVCAsset.service.stock;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootMVCAsset.kafka.model.StockDTO;
import springBootMVCAsset.mapper.StockMapper;

@Service
public class StockListService {
	@Autowired
	StockMapper stockMapper;
	public void execute(Model model) {
		List<StockDTO> list =  stockMapper.stockSelectAll();

		model.addAttribute("stock", list);
	}

}
