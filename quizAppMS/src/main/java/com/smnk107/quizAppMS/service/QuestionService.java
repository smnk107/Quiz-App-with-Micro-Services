package com.smnk107.quizAppMS.service;

import com.smnk107.quizAppMS.dao.QuestionDao;
import com.smnk107.quizAppMS.model.Answer;
import com.smnk107.quizAppMS.model.Question;
import com.smnk107.quizAppMS.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST );
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String type)
    {


        try {
            return new ResponseEntity<>(questionDao.getQuestionsByCategory(type),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST );

    }
    public ResponseEntity<String> addQuestion(Question q)
    {
        try {
            questionDao.save(q);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);
    }
    public ResponseEntity<String> dltById(int id)
    {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);

    }

    public String udtLevel(int id, String diffLevel)
    {
        questionDao.udtLevel(id,diffLevel);
        return "Updated !";
    }


    public List<Integer> generateQuestions(String category, Integer numQ) {
        return questionDao.generateRandomQuestions(category,numQ);
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> idList)
    {
        int n = idList.size();
        List<QuestionWrapper> questionList = new ArrayList<>();

        QuestionWrapper qw ;
        Question question;

        for(int i=0 ; i<n ; i++)
        {
            question = getQueById(idList.get(i));
            qw = new QuestionWrapper();
            qw.setId(question.getId());
            qw.setQuestionTitle(question.getQuestionTitle());
            qw.setOption1(question.getOption1());
            qw.setOption2(question.getOption2());
            qw.setOption3(question.getOption3());
            qw.setOption4(question.getOption4());

            questionList.add(qw);

        }

        return questionList;
    }

    public Question getQueById(int id)
    {
        return questionDao.getQueById(id);
    }

    public ResponseEntity<Integer> getScore(List<Answer> ansList) {
        int res = 0;
        int n = ansList.size();
        String ans =  "";
        for(int i=0 ; i<n ; i++)
        {
            ans = questionDao.getResult(ansList.get(i).getId());
            if(ansList.get(i).getResponse().equals(ans))res++;
            System.out.println(ansList.get(i).getResponse()+"-->"+ans);
        }

        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
