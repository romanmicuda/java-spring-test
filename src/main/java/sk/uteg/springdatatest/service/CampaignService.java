package sk.uteg.springdatatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uteg.springdatatest.api.model.CampaignSummary;
import sk.uteg.springdatatest.api.model.QuestionSummary;
import sk.uteg.springdatatest.db.model.Campaign;
import sk.uteg.springdatatest.db.model.Feedback;
import sk.uteg.springdatatest.db.repository.CampaignRepository;
import sk.uteg.springdatatest.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampaignService implements ICampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IFeedbackService feedbackService;

    @Override
    public CampaignSummary getCampaignSummary(UUID campaignId) throws NotFoundException {
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);

        if (!campaign.isPresent()){
            throw new NotFoundException();
        }

        List<QuestionSummary> questionsSummary = questionService.getQuestionSummariesForCampaign(campaign.get());
        List<Feedback> feedbacks = feedbackService.getFeedbacksByCampaignId(campaignId);
        return new CampaignSummary(feedbacks.size(),questionsSummary);
    }

}
