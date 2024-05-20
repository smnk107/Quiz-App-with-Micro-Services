package com.smnk107.quizService.dao;

import com.smnk107.quizService.model.Question;
import com.smnk107.quizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface quizDao extends JpaRepository<Quiz,Integer> {




    @Query("Select q.questionIds from Quiz q where q.id = ?1")
    List<Integer> getQuiz(int id);
}
