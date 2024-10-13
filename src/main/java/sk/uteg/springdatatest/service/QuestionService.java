package sk.uteg.springdatatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.uteg.springdatatest.api.model.OptionSummary;
import sk.uteg.springdatatest.api.model.QuestionSummary;
import sk.uteg.springdatatest.db.model.*;
import sk.uteg.springdatatest.db.repository.QuestionRepository;
import sk.uteg.springdatatest.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private IAnswerService answerService;

    @Override
    public List<QuestionSummary> getQuestionSummariesForCampaign(Campaign campaign) throws NotFoundException {
        List<Question> questions = questionRepository.findAllByCampaign(campaign);
        return questions.stream().map(question -> processQuestion(question)).collect(Collectors.toList());
    }

    @Override
    public QuestionSummary processQuestion(Question question) {
        QuestionSummary questionSummary = new QuestionSummary();
        questionSummary.setName(question.getText());
        questionSummary.setType(question.getType());

        List<Answer> answers = answerService.getAnswersByQuestionId(question.getId());
        if (question.getType() == QuestionType.RATING) {
            double avgRating = answers.stream()
                    .mapToInt(Answer::getRatingValue)
                    .average()
                    .orElse(0.0);
            questionSummary.setRatingAverage(BigDecimal.valueOf(avgRating));
            questionSummary.setOptionSummaries(Collections.emptyList());
        } 
        else if (question.getType() == QuestionType.CHOICE) {
            Map<String, Long> optionCountMap = answers.stream()
                .flatMap(answer -> answer.getSelectedOptions().stream())
                .collect(Collectors.groupingBy(Option::getText, Collectors.counting()));
        
            List<OptionSummary> optionSummaries = question.getOptions().stream()
                .map(option -> new OptionSummary(option.getText(), optionCountMap.getOrDefault(option.getText(), 0L).intValue()))
                .collect(Collectors.toList());
        
            questionSummary.setOptionSummaries(optionSummaries);
            questionSummary.setRatingAverage(BigDecimal.ZERO);
        }
        return questionSummary;
    }
}
