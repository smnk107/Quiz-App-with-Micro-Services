package com.smnk107.quizService.model;

import lombok.Data;

@Data
public class quizDto {
    String category;
    String diffLevel;
    String title;
    Integer numQ;
}
