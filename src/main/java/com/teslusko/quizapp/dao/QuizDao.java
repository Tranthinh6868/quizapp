package com.teslusko.quizapp.dao;

import com.teslusko.quizapp.model.Question;
import com.teslusko.quizapp.model.Quiz;
import com.teslusko.quizapp.service.QuizService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
