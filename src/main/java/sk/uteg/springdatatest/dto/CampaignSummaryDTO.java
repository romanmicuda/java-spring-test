package sk.uteg.springdatatest.dto;

import java.util.List;

import lombok.Data;

@Data
public class CampaignSummaryDTO {
    private long totalFeedbacks;
    private List<QuestionSummaryDTO> questionsSummary;
}