package sk.uteg.springdatatest.service;

import java.util.UUID;

import sk.uteg.springdatatest.api.model.CampaignSummary;
import sk.uteg.springdatatest.exception.NotFoundException;

public interface ICampaignService {
    CampaignSummary getCampaignSummary(UUID campaignId) throws NotFoundException;
}
