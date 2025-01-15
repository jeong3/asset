package springBootMVCAsset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.AuthInfoDTO;
import springBootMVCAsset.domain.KakaoUser;
import springBootMVCAsset.domain.SalaryDTO;
import springBootMVCAsset.domain.StockDataDTO;
import springBootMVCAsset.mapper.EmployeeMapper;
import springBootMVCAsset.mapper.EvalMapper;
import springBootMVCAsset.mapper.NewsMapper;
import springBootMVCAsset.service.FileDelService;
import springBootMVCAsset.service.KakaoService;
import springBootMVCAsset.service.MemberService;
import springBootMVCAsset.service.attend.AttendEndService;
import springBootMVCAsset.service.attend.AttendStartService;
import springBootMVCAsset.service.goods.SaleRegistService;
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
	@Autowired
	SaleRegistService saleRegistService;
	@Autowired
	AttendStartService attendStartService;
	@Autowired
	AttendEndService attendEndService;
	@Autowired
	EvalMapper evalMapper;
	@Autowired
	KakaoService kakaoService;
	@Autowired
	MemberService memberService;
	
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
	@RequestMapping("/news/deleteSave")
	public int deleteSave(String newsNum, HttpSession session) {
		System.out.println("뉴스번호"+newsNum);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		newsMapper.deleteSave(newsNum, auth.getUserNum());
		return 200;
	}
	@RequestMapping("/news/deleteLike")
	public int deleteLike(String newsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		newsMapper.deleteLike(newsNum, auth.getUserId());
		return 200;
	}
	@GetMapping("/stock/stockData")
    public List<StockDataDTO> getStockData() {
        List<StockDataDTO> stockData = stockListService.execute();
        return stockData;
    }
	@GetMapping("/stock/saleRegist")
	public String saleRegist() {
		saleRegistService.execute();
		return "200";
	}
	@RequestMapping("/attend/attendStart")
	public String attendStart(String empNum) {
		attendStartService.execute(empNum);
		return "200";
	}
	@RequestMapping("/attend/attendEnd")
	public String attendEnd(String empNum, String attendNum) {
	
		attendEndService.execute(empNum, attendNum);
		return "200";
	}
	@RequestMapping("/eval/checkCount")
	public int checkCount(String empNum) {
		return evalMapper.checkCount(empNum); 
	}
	@RequestMapping("/eval/moneyCheck")
	public String moneyCheck(@RequestBody SalaryDTO dto) {
		System.out.println(dto);
		evalMapper.moneyCheck(dto);
		return "200"; 
	}
	@PostMapping("/kakao-login")
	public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> requestBody, HttpSession session) {
	    String accessToken = requestBody.get("accessToken");
	    
	    // 카카오 API를 통해 사용자 정보 요청
	    KakaoUser kakaoUser = kakaoService.getKakaoUserInfo(accessToken);
	    
	    // 사용자 정보를 기반으로 회원가입 또는 로그인 처리
	    AuthInfoDTO auth = memberService.findOrCreateMember(kakaoUser);
	    System.out.println("디티오:"+auth);
	    // 세션에 사용자 정보 저장 또는 로그인 처리
	    session.setAttribute("auth", auth);

	    return ResponseEntity.ok(auth);
	}
	@PostMapping("/naver-login")
	public ResponseEntity<?> naverLogin(@RequestBody Map<String, String> requestBody, HttpSession session) {
	    String accessToken = requestBody.get("accessToken");
	    // 네이버 API를 호출하여 사용자 정보 가져오기
	    String apiURL = "https://openapi.naver.com/v1/nid/me";
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Bearer " + accessToken);
	    
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    RestTemplate restTemplate = new RestTemplate();
	    
	    ResponseEntity<String> response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);
	    KakaoUser kakaoUser = new KakaoUser();
	    kakaoUser.setId("naverId");
	 // 사용자 정보를 기반으로 회원가입 또는 로그인 처리
	    AuthInfoDTO auth = memberService.findOrCreateMember(kakaoUser);
	    System.out.println("auth"+auth);
	    // 세션에 사용자 정보 저장 또는 로그인 처리
	    session.setAttribute("auth", auth);
	    
	    return ResponseEntity.ok(response.getBody());
	}

	
	
}
