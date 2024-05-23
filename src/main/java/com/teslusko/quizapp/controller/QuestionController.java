package com.teslusko.quizapp.controller;

import com.teslusko.quizapp.model.Question;
import com.teslusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{QuestionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer QuestionId) {
        return questionService.getQuestion(QuestionId);
    }

    @PostMapping("add")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/{Id}")
    public ResponseEntity <Question> updateQuestion(@PathVariable Integer Id, @RequestBody Question question) {
        return questionService.updateQuestion(Id, question);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer Id) {
       return questionService.deleteQuestion(Id);

    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }


}
