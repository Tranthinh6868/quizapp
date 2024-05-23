package com.teslusko.quizapp.controller;

import com.teslusko.quizapp.Question;
import com.teslusko.quizapp.service.QuestionService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{QuestionId}")
    public Question getQuestionById(@PathVariable Integer QuestionId) {
        return questionService.getQuestion(QuestionId);
    }

    @PostMapping("add")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/{Id}")
    public Question updateQuestion(@PathVariable Integer Id, @RequestBody Question question) {
        return questionService.updateQuestion(Id, question);
    }

    @DeleteMapping("/{Id}")
    public String deleteQuestion(@PathVariable Integer Id) {
        questionService.deleteQuestion(Id);
        return "Question deleted";
    }
    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }


}
