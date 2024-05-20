package com.smnk107.quizService.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Answer {
    private int id;
    private String response;
}
