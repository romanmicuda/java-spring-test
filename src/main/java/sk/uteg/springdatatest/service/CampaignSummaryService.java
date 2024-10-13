package sk.uteg.springdatatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sk.uteg.springdatatest.db.model.*;
import sk.uteg.springdatatest.db.repository.*;
import sk.uteg.springdatatest.dto.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignSummaryService {

    private final CampaignRepository campaignRepository;
    private final FeedbackRepository feedbackRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public CampaignSummaryDTO getCampaignSummary(UUID campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        long totalFeedbacks = feedbackRepository.countByCampaign(campaign);

        List<QuestionSummaryDTO> questionsSummary = questionRepository.findAllByCampaign(campaign).stream()
                .map(this::getQuestionSummary)
                .collect(Collectors.toList());

        CampaignSummaryDTO summaryDTO = new CampaignSummaryDTO();
        summaryDTO.setTotalFeedbacks(totalFeedbacks);
        summaryDTO.setQuestionsSummary(questionsSummary);

        return summaryDTO;
    }

    private QuestionSummaryDTO getQuestionSummary(Question question) {
        QuestionSummaryDTO questionSummaryDTO = new QuestionSummaryDTO();
        questionSummaryDTO.setText(question.getText());
        questionSummaryDTO.setType(question.getType().toString());

        if (question.getType() == QuestionType.RATING) {
            double avgRating = answerRepository.findAllByQuestion(question).stream()
                    .mapToInt(Answer::getRatingValue)
                    .average()
                    .orElse(0.0);
            questionSummaryDTO.setAverageRating(avgRating);
            questionSummaryDTO.setOptionCounts(null);
        } else if (question.getType() == QuestionType.CHOICE) {
            Map<String, Long> optionCountMap = new HashMap<>();

            for (Option option : question.getOptions()) {
                optionCountMap.put(option.getText(), 0L);
            }

            answerRepository.findAllByQuestion(question).forEach(answer -> {
                for (Option selectedOption : answer.getSelectedOptions()) {
                    optionCountMap.put(selectedOption.getText(), optionCountMap.getOrDefault(selectedOption.getText(), 0L) + 1);
                }
            });

            List<OptionCountDTO> optionCounts = optionCountMap.entrySet().stream()
                    .map(entry -> {
                        OptionCountDTO dto = new OptionCountDTO();
                        dto.setOptionText(entry.getKey());
                        dto.setCount(entry.getValue());
                        return dto;
                    })
                    .collect(Collectors.toList());

            questionSummaryDTO.setOptionCounts(optionCounts);
            questionSummaryDTO.setAverageRating(null);
        }

        return questionSummaryDTO;
    }
}
