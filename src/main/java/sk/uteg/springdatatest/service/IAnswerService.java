package sk.uteg.springdatatest.service;

import sk.uteg.springdatatest.db.model.Answer;

import java.util.List;
import java.util.UUID;

public interface IAnswerService {
    List<Answer> getAnswersByQuestionId(UUID questionId);
}
