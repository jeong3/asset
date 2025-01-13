package springBootMVCAsset.service.volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootMVCAsset.mapper.VolunteerMapper;

@Service
public class VolunteerResultUpdateService {
    @Autowired
    VolunteerMapper volunteerMapper;

    public void updateResult(String receiptNum, String documentResult) {
        volunteerMapper.updateResult(receiptNum, documentResult);
    }

    public void updateInterviewResult(String receiptNum, String interviewResult) {
        volunteerMapper.updateInterviewResult(receiptNum, interviewResult);
    }
}
