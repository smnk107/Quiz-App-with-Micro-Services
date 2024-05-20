package com.smnk107.quizAppMS.controller;

import com.smnk107.quizAppMS.model.Answer;
import com.smnk107.quizAppMS.model.Question;
import com.smnk107.quizAppMS.model.QuestionWrapper;
import com.smnk107.quizAppMS.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService service;
    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return service.getAllQuestions();
    }

    @GetMapping("category/{type}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("type") String type)
    {
        return service.getQuestionsByCategory(type);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question q)
    {
        return service.addQuestion(q);
    }
    @PostMapping("deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id)
    {
        return service.dltById(id);
    }

    @PostMapping("updateDiffLevel/{id}")
    public String udtLevel(@PathVariable("id") int id, @RequestBody String diffLevel)
    {
        return  service.udtLevel(id,diffLevel);
    }

    //generate ques
    //get questions based on question id
    //calculate score

    @GetMapping("generateQuestions")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category,@RequestParam Integer numQ)
    {
        List<Integer> list = service.generateQuestions(category,numQ);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("getQuestionsFromId")
    public ResponseEntity<List<QuestionWrapper>> quesFromId(@RequestBody  List<Integer> lsId)
    {
        List<QuestionWrapper> lsQw = service.getQuestionsFromId(lsId);
        return new ResponseEntity<>(lsQw ,HttpStatus.OK);
    }

    @PostMapping("calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Answer> anss)
    {
        return service.getScore(anss);
    }


}
