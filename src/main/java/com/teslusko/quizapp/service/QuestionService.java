package com.teslusko.quizapp.service;

import com.teslusko.quizapp.Question;
import com.teslusko.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    public QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
    try {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }
    catch (Exception e) {
        e.printStackTrace();
    }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> getQuestion(int questionId) {
        try {
            return new ResponseEntity<>(questionDao.findById(questionId).get(),HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> createQuestion(Question question) {
        Question savedQuestion = questionDao.save(question);
        try {
            return new ResponseEntity<>(getQuestion(savedQuestion.getId()).getBody(),HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Question> updateQuestion(Integer id, Question question) {
        List<Question> questions = getAllQuestions().getBody();
        for(Question q: questions) {
            if(q.getId().equals(id)) {
                q.setCategory(question.getCategory());
                q.setQuestionTitle(question.getQuestionTitle());
                q.setOption1(question.getOption1());
                q.setOption2(question.getOption2());
                q.setOption3(question.getOption3());
                q.setOption4(question.getOption4());
                q.setDifficultyLevel(question.getDifficultyLevel());
                q.setRightAnswer(question.getRightAnswer());
                questionDao.save(q);
                try {
                    return new ResponseEntity<>(getQuestion(id).getBody(), HttpStatus.OK);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted", HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question can not deleted", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
//        List<Question> questions = new ArrayList<Question>();
//        for(Question q: questionDao.findAll()) {
//            if(q.getCategory().equals(category)) {
//                questions.add(q);
//            }
//        }
//        return questions;
    }
}
