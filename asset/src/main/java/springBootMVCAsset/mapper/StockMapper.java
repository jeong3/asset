package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import springBootMVCAsset.kafka.model.StockDTO;

@Mapper
public interface StockMapper {
	
    List<StockDTO> stockSelectAll();

}
