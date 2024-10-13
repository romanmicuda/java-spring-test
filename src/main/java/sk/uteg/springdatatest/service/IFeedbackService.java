package sk.uteg.springdatatest.service;

import sk.uteg.springdatatest.db.model.Feedback;
import sk.uteg.springdatatest.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface IFeedbackService {
    List<Feedback> getFeedbacksByCampaignId(UUID campaignId) throws NotFoundException;
}
