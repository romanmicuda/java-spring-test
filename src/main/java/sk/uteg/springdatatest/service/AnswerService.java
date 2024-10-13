package sk.uteg.springdatatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.uteg.springdatatest.db.model.Answer;
import sk.uteg.springdatatest.db.repository.AnswerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService implements IAnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAnswersByQuestionId(UUID questionId){
        return answerRepository.findAllByQuestionId(questionId);
    }
}
