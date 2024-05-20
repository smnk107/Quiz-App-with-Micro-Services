package com.smnk107.quizService.controller;

import com.smnk107.quizService.model.Answer;
import com.smnk107.quizService.model.Question;
import com.smnk107.quizService.model.QuestionWrapper;
import com.smnk107.quizService.model.quizDto;
import com.smnk107.quizService.service.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    quizService service;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody quizDto qdto)
    {
        return service.createQuiz(qdto.getCategory(), qdto.getDiffLevel(), qdto.getNumQ(), qdto.getTitle());

        //return new ResponseEntity<>("I am here", HttpStatus.ACCEPTED );

    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id)
    {
        return service.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submit(@PathVariable("id") int quizId,@RequestBody List<Answer> ansList)
    {
        return service.getResult(quizId,ansList);
    }

}
