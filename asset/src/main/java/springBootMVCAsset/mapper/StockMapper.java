package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.StockDataDTO;
import springBootMVCAsset.kafka.model.StockDTO;

@Mapper
public interface StockMapper {
	
    List<StockDataDTO> stockDataSelect();

	double closeSelect();

	int saleInsert(double sale);

}
