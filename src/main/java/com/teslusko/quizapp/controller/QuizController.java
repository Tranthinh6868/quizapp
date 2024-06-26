package com.teslusko.quizapp.controller;

import com.teslusko.quizapp.model.Question;
import com.teslusko.quizapp.model.QuestionWrapper;
import com.teslusko.quizapp.model.Quiz;
import com.teslusko.quizapp.model.Response;
import com.teslusko.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category
                                            ,@RequestParam int numQ ,
                                             @RequestParam String title) {
           return quizService.createQuiz(category,numQ, title);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id) {
        return  quizService.getQuizById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id) {
        return quizService.deleteQuiz(id);
    }
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return quizService.getAllQuiz();
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuestion(@PathVariable Integer id, @RequestBody List<Response> responseList) {
        return quizService.submitQuestion(id, responseList);
    }
}
