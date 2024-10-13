package sk.uteg.springdatatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.uteg.springdatatest.db.model.Feedback;
import sk.uteg.springdatatest.db.repository.FeedbackRepository;
import sk.uteg.springdatatest.exception.NotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getFeedbacksByCampaignId(UUID campaignId) throws NotFoundException {
        return feedbackRepository.findAllByCampaignId(campaignId);
    }

}
