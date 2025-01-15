package springBootMVCAsset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springBootMVCAsset.domain.AnnounceDTO;
import springBootMVCAsset.domain.VolunteerDTO;
import springBootMVCAsset.mapper.AnnounceMapper;
import springBootMVCAsset.mapper.VolunteerMapper;
import springBootMVCAsset.service.EmailSendService;
import springBootMVCAsset.service.volunteer.VolunteerIdCheckService;
import springBootMVCAsset.service.volunteer.VolunteerListService;
import springBootMVCAsset.service.volunteer.VolunteerResultUpdateService;

@Controller
@RequestMapping("volunteer")
public class VolunteerController {
	@Autowired
	AnnounceMapper announceMapper;
	@Autowired
	VolunteerIdCheckService volunteerIdCheckService;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	VolunteerMapper volunteerMapper;
	@Autowired
	VolunteerResultUpdateService volunteerResultUpdateService;
	@Autowired
	VolunteerListService volunteerListService;
	@GetMapping("volunteerList")
	public String volunteerList(Model model) {
		volunteerListService.execute(model);
		return "thymeleaf/volunteer/volunteerList";
	}
	@PostMapping("updateResult")
    public String updateResult(@RequestParam("receiptNum") String receiptNum, 
                               @RequestParam("documentResult") String documentResult) {
        volunteerResultUpdateService.updateResult(receiptNum, documentResult);
        
        VolunteerDTO dto = volunteerMapper.findByReceiptNum(receiptNum);

        // 서류 전형 결과가 합격일 때
        if ("합격".equals(documentResult)) {
            // 서류 합격 이메일 발송
            String fromEmail = "asset@naver.com";  // 발신 이메일 주소
            String toEmail = dto.getVolunteerEmail();  // 지원자의 이메일 주소
            String subject = "서류 전형 합격";
            String contents = "축하드립니다! 서류 전형에 합격하셨습니다. 면접 전형에 참여해 주세요.";
            
            // 서류 합격 이메일 발송
            emailSendService.mailSend(fromEmail, toEmail, subject, contents);
        } 
        // 서류 전형 결과가 불합격일 때
        else if ("불합격".equals(documentResult)) {
            // 서류 불합격 이메일 발송
            String fromEmail = "asset@naver.com";  // 발신 이메일 주소
            String toEmail = dto.getVolunteerEmail();  // 지원자의 이메일 주소
            String subject = "서류 전형 불합격";
            String contents = "안타깝게도 서류 전형에서 불합격되었습니다. 다음 기회에 다시 도전해 주세요.";
            
            // 서류 불합격 이메일 발송
            emailSendService.mailSend(fromEmail, toEmail, subject, contents);
        }
        return "redirect:/volunteer/volunteerList";
    }

    @PostMapping("updateInterviewResult")
    public String updateInterviewResult(@RequestParam("receiptNum") String receiptNum, 
                                        @RequestParam("interviewResult") String interviewResult) {
        volunteerResultUpdateService.updateInterviewResult(receiptNum, interviewResult);
        
        VolunteerDTO dto = volunteerMapper.findByReceiptNum(receiptNum);
        if ("합격".equals(dto.getDocumentResult()) && "합격".equals(interviewResult)) {
            // 이메일 발송
            String fromEmail = "asset@naver.com";  // 발신 이메일 주소
            String toEmail = dto.getVolunteerEmail();  // 지원자의 이메일 주소
            String subject = "면접 합격";
            String contents = "축하드립니다! 최종 합격하셨습니다.";
            
            // 이메일 발송
            emailSendService.mailSend(fromEmail, toEmail, subject, contents);
        }
        else if ("불합격".equals(interviewResult)) {
            // 이메일 발송 (불합격)
            String fromEmail = "asset@naver.com";  // 발신 이메일 주소
            String toEmail = dto.getVolunteerEmail();  // 지원자의 이메일 주소
            String subject = "면접 불합격";
            String contents = "안타깝게도 면접 결과 불합격입니다. 다음 기회에 다시 도전해 주세요.";
            
            // 불합격 이메일 발송
            emailSendService.mailSend(fromEmail, toEmail, subject, contents);
        }
        return "redirect:/volunteer/volunteerList";
    }
    @PostMapping("idCheck")
	public @ResponseBody Integer userIdCheck(String volunteerId) {
		System.out.println("volunteerId : " + volunteerId);
		Integer i = volunteerIdCheckService.execute(volunteerId);
		System.out.println(i+"컨트롤러");
		return i;
	}
    @GetMapping("receiptCheck")
    public String receiptCheck() {
    	return "thymeleaf/volunteer/receiptCheck";
    }
    @PostMapping("checkResult")
    public String checkResult(@RequestParam("volunteerId") String volunteerId, Model model) {
    	System.out.println("지원자 아이디 : " + volunteerId);
    	VolunteerDTO dto = volunteerMapper.findByVolunteerId(volunteerId);
    	String announceNum = volunteerMapper.selectAnnounceNum(volunteerId);
    	System.out.println("공고 : " + announceNum);
    	AnnounceDTO announce = announceMapper.announceSelectOne(announceNum);
    	model.addAttribute("announce", announce);
    	model.addAttribute("dto", dto);
    	model.addAttribute("volunteerId", volunteerId);
    	return "thymeleaf/volunteer/checkResult";
    }
}
