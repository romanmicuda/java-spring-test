package sk.uteg.springdatatest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CampaignSummary {
    private long totalFeedbacks;
    private List<QuestionSummary> questionSummaries;
}
