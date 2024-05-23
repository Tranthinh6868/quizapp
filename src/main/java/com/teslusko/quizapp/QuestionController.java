package com.teslusko.quizapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {

    @RequestMapping("allQuestions")
    public String getAllQuestions() {
        return "All questions";
    }


}
