package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.NewsAnalyzeDTO;
import springBootMVCAsset.domain.NewsCommentDTO;
import springBootMVCAsset.domain.NewsDTO;
import springBootMVCAsset.domain.StartEndPageDTO;

@Mapper
public interface NewsMapper {

	int newsInsert(NewsDTO dto);

	List<NewsDTO> newsSelectAll(StartEndPageDTO sepDTO);

	NewsDTO newsSelectOne(String newsNum);

	int newsUpdate(NewsDTO dto);

	int newsDelete(String newsNum);

	int countUpdate(String newsNum);

	int updateLikeCount(String newsNum, String memberId);

	int selectLikeCount(String newsNum);

	String newsAnalyzeSelect(String newsNum, String memberId);

	int newsAnalyzeCount(String newsNum);

	int commentInsert(NewsCommentDTO dto);

	List<NewsCommentDTO> commentSelectAll(String newsNum);

	int newsSaveUpdate(String newsNum, String memberNum);

	String newsSaveSelect(String newsNum, String memberNum);

	int commentDelete(String commentNum);

	List<NewsDTO> newsLoadMoreSelect(int startRow, int endRow);

	Integer newsCount();

}
