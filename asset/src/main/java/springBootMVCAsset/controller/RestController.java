package springBootMVCAsset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.StockDataDTO;
import springBootMVCAsset.mapper.EmployeeMapper;
import springBootMVCAsset.mapper.NewsMapper;
import springBootMVCAsset.service.FileDelService;
import springBootMVCAsset.service.news.NewsAnalyzeUpdate;
import springBootMVCAsset.service.news.SaveRegistService;
import springBootMVCAsset.service.stock.StockListService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	FileDelService fileDelService;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	NewsAnalyzeUpdate newsAnalyzeUpdate;
	@Autowired
	SaveRegistService saveRegistService;
	@Autowired
	StockListService stockListService;
	@Autowired
	NewsMapper newsMapper;
	
	@PostMapping("/file/fileDel")
	public int fileDel(String orgFile, String storeFile, HttpSession session) {
		return fileDelService.execute(orgFile, storeFile, session);
   }
	@RequestMapping("/employee/setDepartment")
	public void setDepartment(@RequestBody Map<String, Object> map) {
		employeeMapper.setDepartmentNumUpdate(map.get("departmentNum").toString(), map.get("empNum").toString());
	}
	@RequestMapping("/news/updateLikeCount")
	public String updateLikeCount(@RequestParam String newsNum, HttpSession session, Model model) {
		System.out.println("뉴스번호"+newsNum);
		String recommendDate = newsAnalyzeUpdate.execute(newsNum, session, model);
		return recommendDate;
	}
	@RequestMapping("/news/newsSave")
	public String newsSave(@RequestParam("newsNum") String newsNum, HttpSession session) {
		String saveDate = saveRegistService.execute(newsNum, session);
		return saveDate;
	}

	@GetMapping("/stock/stockData")
    public List<StockDataDTO> getStockData() {
        List<StockDataDTO> stockData = stockListService.execute();
        return stockData;
    }
	@RequestMapping("/news/deleteSave")
	public int deleteSave(String newsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		//newsMapper.deleteSave(auth.get)
		return 200;
	}
	@RequestMapping("/news/deleteLike")
	public int deleteLike(String newsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		//newsMapper.deleteLike(auth.get)q
		return 200;
	}
	
	
}
