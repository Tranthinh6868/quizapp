package com.teslusko.quizapp.dao;

import com.teslusko.quizapp.model.Question;
import com.teslusko.quizapp.model.Quiz;
import com.teslusko.quizapp.service.QuizService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
