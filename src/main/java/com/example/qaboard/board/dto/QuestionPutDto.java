package com.example.qaboard.board.dto;

import lombok.Getter;

@Getter
public class QuestionPutDto {

    long memberId;
    long questionId;

    String title;
    String content;

    int postStatus;
    int questionStatus;
}
