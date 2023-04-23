package com.example.qaboard.board.dto;

import com.example.qaboard.board.entity.Question;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class QuestionResponseDto {


    private String writer; // questionId -> memberId -> members.name
    private long questionId;

    private String title;
    private String content;

    //int postStatus; //공개 비공개
    //int questionStatus; //상태 필요없음! 디비에서 검색할거임.

    public static QuestionResponseDto of(Question question, String name) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.questionId=question.getQuestionId();
        questionResponseDto.title = question.getTitle();
        questionResponseDto.content = question.getContent();
        questionResponseDto.writer = name;

        return questionResponseDto;
    }

}
