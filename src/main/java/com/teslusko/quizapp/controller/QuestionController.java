package com.teslusko.quizapp.controller;

import com.teslusko.quizapp.Question;
import com.teslusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/{Id}")
    public Question updateQuestion(@PathVariable Integer Id, @RequestBody Question question) {
        return questionService.updateQuestion(Id, question);
    }



}
