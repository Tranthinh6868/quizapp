package com.teslusko.quizapp.service;

import com.teslusko.quizapp.dao.QuestionDao;
import com.teslusko.quizapp.dao.QuizDao;
import com.teslusko.quizapp.model.Question;
import com.teslusko.quizapp.model.QuestionWrapper;
import com.teslusko.quizapp.model.Quiz;
import com.teslusko.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
        try {

            Quiz quiz = quizDao.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            List<QuestionWrapper> questionWrappers = new ArrayList<QuestionWrapper>();
            for (Question question : questions) {
                QuestionWrapper questionWrapper = new QuestionWrapper();
                questionWrapper.setId(question.getId());
                questionWrapper.setQuestionTitle(question.getQuestionTitle());
                questionWrapper.setOption1(question.getOption1());
                questionWrapper.setOption2(question.getOption2());
                questionWrapper.setOption3(question.getOption3());
                questionWrapper.setOption4(question.getOption4());
                questionWrappers.add(questionWrapper);
            }

            return new ResponseEntity<List<QuestionWrapper>>(questionWrappers, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteQuiz(Integer id) {
        try{
            quizDao.deleteById(id);
            return ResponseEntity.ok("Quiz has been deleted.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Quiz has not been founded and has not been deleted.", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Quiz>> getAllQuiz() {
        try{
            return new ResponseEntity<>(quizDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> submitQuestion(Integer id, List<Response> responses) {
        List<Question> questions = quizDao.findById(id).get().getQuestions();
        int score = 0;
        int i =0;

        for (Question question : questions) {
            if(question.getRightAnswer().equals(responses.get(i).getResponse())){
                score ++;
            }
            i++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);

    }
}
