package com.teslusko.quizapp.service;

import com.teslusko.quizapp.Question;
import com.teslusko.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    public QuestionDao questionDao;

    public List<Question> getAllQuestions() {

          return questionDao.findAll();
    }

    public Question getQuestion(int questionId) {
        return questionDao.findById(questionId).get();
    }

    public Question createQuestion(Question question) {
        Question savedQuestion = questionDao.save(question);
        return getQuestion(savedQuestion.getId());

    }

    public Question updateQuestion(Integer id, Question question) {
        List<Question> questions = getAllQuestions();
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
                return getQuestion(id);
            }
        }
        return null;
    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category);
//        List<Question> questions = new ArrayList<Question>();
//        for(Question q: questionDao.findAll()) {
//            if(q.getCategory().equals(category)) {
//                questions.add(q);
//            }
//        }
//        return questions;
    }
}
