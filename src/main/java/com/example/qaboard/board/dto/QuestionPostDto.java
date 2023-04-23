package com.example.qaboard.board.dto;

import lombok.Getter;

@Getter
public class QuestionPostDto {

    long memberId;
    //이게 여기에 필요한 걸까? 세큐리티 사용하면 헤드값으로 사용자 정보가 넘어가지 않나?

    String title;
    String content;

    int postStatus; //공개 비공개 > 애초에 걸러져서 나오면 될걱 같음.
    //int questionStatus; //상태 필요없음! 디비에서 검색할거임.

}