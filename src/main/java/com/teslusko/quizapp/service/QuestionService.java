package com.teslusko.quizapp.service;

import com.teslusko.quizapp.Question;
import com.teslusko.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    public QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {

          return questionRepository.findAll();
    }

    public Question getQuestion(int questionId) {
        return questionRepository.findById(questionId).get();
    }
}
