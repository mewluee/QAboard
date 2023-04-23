package com.example.qaboard.board.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionListResponseDto {

    private List<QuestionResponseDto> questionResponses;
}
