package com.smnk107.quizService.service;


import com.smnk107.quizService.dao.quizDao;
import com.smnk107.quizService.feign.feignInterface;
import com.smnk107.quizService.model.Answer;
import com.smnk107.quizService.model.Question;
import com.smnk107.quizService.model.QuestionWrapper;
import com.smnk107.quizService.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class quizService {
    @Autowired
    quizDao quizdao;


    @Autowired
    feignInterface  feigninterface ;

    public ResponseEntity<String> createQuiz(String category, String diffLevel, int numQ, String title) {



        List<Integer> idList = feigninterface.generateQuestions(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(idList);
        quizdao.save(quiz);

        return  new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {

        List<Integer> list = quizdao.getQuiz(id);
        List<QuestionWrapper> lsQw = feigninterface.getQuestionsFromId(list);
        return new ResponseEntity<>(lsQw,HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> getResult(int quizId,List<Answer> ansList)
    {
        int score = feigninterface.getScore(ansList).getBody();
        return new ResponseEntity<>(score,HttpStatus.OK);

    }
}
