package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.NewsAnalyzeDTO;
import springBootMVCAsset.domain.NewsDTO;

@Mapper
public interface NewsMapper {

	int newsInsert(NewsDTO dto);

	List<NewsDTO> newsSelectAll();

	NewsDTO newsSelectOne(String newsNum);

	int newsUpdate(NewsDTO dto);

	int newsDelete(String newsNum);

	int newsAnalyzeMerge(NewsAnalyzeDTO newsAnalyzeDTO);

}
