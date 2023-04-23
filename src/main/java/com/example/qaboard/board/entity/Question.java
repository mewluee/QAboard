package com.example.qaboard.board.entity;


import com.example.qaboard.board.dto.QuestionPostDto;
import com.example.qaboard.board.dto.QuestionPutDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false, updatable = false)
    private long memberId;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PostStatus postStatus;


    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    //추가함.
    //@Column
    //private long register_ans=0;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QuestionStatus questionStatus=QuestionStatus.QUESTION_REGISTERED;


    // enum 2개
    public enum QuestionStatus {
        QUESTION_REGISTERED("질문 등록 상태",0),
        QUESTION_ANSWERED("답변 완료 상태",1),
        QUESTION_DELETED("질문 삭제 상태",2),
        QUESTION_DEACTIVED("질문 비활성화 상태",3);

        @Getter
        private String strStatus;
        private int numStatus;

        QuestionStatus(String strStatus, int numStatus) {
            this.strStatus = strStatus;
            this.numStatus = numStatus;
        }

        // postDto에서 상수 1로만 값을 넣기 위한 메서드
        public static QuestionStatus fromInt(int numStatus) {
            for (QuestionStatus value : values()) {
                if (value.numStatus == numStatus) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid secretNum: " + numStatus);
        }
    }

    public enum PostStatus{
        PUBLIC("공개글",0),
        SECRET("비밀글",1);

        @Getter
        private String strStatus;
        private int numStatus;

        PostStatus(String strStatus, int numStatus){
            this.strStatus = strStatus;
            this.numStatus = numStatus;
        }

        // postDto에서 상수 1로만 값을 넣기 위한 메서드
        public static PostStatus fromInt(int numStatus) {
            for (PostStatus value : values()) {
                if (value.numStatus == numStatus) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid secretNum: " + numStatus);
        }
    }

    // 그런데 정적 팩토리 메서드 쓰면 엔티티의 개념과는 조금 멀어지는 거 같아.
    // 생성자 대신 쓰는 정적 팩토리 메서드
    public static Question of(QuestionPostDto questionPostDto) {
        Question question = new Question();

        question.memberId = questionPostDto.getMemberId();
        question.title = questionPostDto.getTitle();
        question.content = questionPostDto.getContent();
        question.postStatus = PostStatus.fromInt(questionPostDto.getPostStatus());

        return question;
    }

    public static Question of(QuestionPutDto questionPutDto) {
        Question question = new Question();

        question.memberId = questionPutDto.getMemberId();
        question.questionId = questionPutDto.getQuestionId();
        question.title = questionPutDto.getTitle();
        question.content = questionPutDto.getContent();
        question.postStatus = PostStatus.fromInt(questionPutDto.getPostStatus());
        question.questionStatus = QuestionStatus.fromInt(questionPutDto.getQuestionStatus());

        return question;
    }

}