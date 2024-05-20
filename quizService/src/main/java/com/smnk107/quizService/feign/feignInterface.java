package com.smnk107.quizService.feign;

import com.smnk107.quizService.model.Answer;
import com.smnk107.quizService.model.Question;
import com.smnk107.quizService.model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
@FeignClient("QUIZAPPMS")
public interface feignInterface {

    @GetMapping("questions/generateQuestions")
    public List<Integer> generateQuestions(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("questions/getQuestionsFromId")
    public List<QuestionWrapper> getQuestionsFromId(@RequestBody List<Integer> idList);



    @PostMapping("questions/calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Answer> ansList);

}
