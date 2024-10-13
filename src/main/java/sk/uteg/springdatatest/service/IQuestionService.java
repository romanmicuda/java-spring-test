package sk.uteg.springdatatest.service;
import sk.uteg.springdatatest.api.model.QuestionSummary;
import sk.uteg.springdatatest.db.model.Campaign;
import sk.uteg.springdatatest.db.model.Question;
import sk.uteg.springdatatest.exception.NotFoundException;

import java.util.List;

public interface IQuestionService {
    List<QuestionSummary> getQuestionSummariesForCampaign(Campaign campaign) throws NotFoundException;
    QuestionSummary processQuestion(Question question);
}
