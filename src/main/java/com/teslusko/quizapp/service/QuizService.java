package com.teslusko.quizapp.service;

import com.teslusko.quizapp.dao.QuestionDao;
import com.teslusko.quizapp.dao.QuizDao;
import com.teslusko.quizapp.model.Question;
import com.teslusko.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<String>("Quiz has been created.",HttpStatus.CREATED);
    }

}
