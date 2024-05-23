package com.teslusko.quizapp.repository;

import com.teslusko.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository  extends JpaRepository<Question, Integer> {

}
