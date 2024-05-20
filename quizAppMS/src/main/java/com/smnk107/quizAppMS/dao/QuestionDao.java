package com.smnk107.quizAppMS.dao;

import com.smnk107.quizAppMS.model.Question;
import com.smnk107.quizAppMS.model.QuestionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>
{
    @Query("select q from Question q where q.category = ?1")
    public List<Question> getQuestionsByCategory(String type);

    @Query("delete from Question q where q.id = ?1")
    public void dltById(int id);

    @Query("update Question q set q.difficultylevel = ?2 where q.id = ?1")
    public void udtLevel(int id, String diffLevel);


    @Query(nativeQuery = true,value = "SELECT * " +
            "FROM QUESTION Q " +
            "WHERE Q.category = :category " +
            "and Q.difficultylevel = :diffLevel" +
            " ORDER BY RANDOM()" +
            " LIMIT :numQ")
    List<Question> getRandomQuestionsBycategory(String category, String diffLevel, int numQ);


    @Query(nativeQuery = true,value = "SELECT Q.id " +
            "FROM QUESTION Q " +
            "WHERE Q.category = :category " +
            " ORDER BY RANDOM()" +
            " LIMIT :numQ")
    List<Integer> generateRandomQuestions(String category, Integer numQ);

    @Query("Select Q from Question Q where Q.id = ?1")
    public Question getQueById(int id);

    @Query(nativeQuery = true,value = "select q.right_answer from Question q where q.id = :id")
    String getResult(int id);
}
