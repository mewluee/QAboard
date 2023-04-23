package com.example.qaboard.board.controller;

import com.example.qaboard.board.dto.QuestionListResponseDto;
import com.example.qaboard.board.dto.QuestionPostDto;
import com.example.qaboard.board.dto.QuestionPutDto;
import com.example.qaboard.board.dto.QuestionResponseDto;
import com.example.qaboard.board.entity.Question;
import com.example.qaboard.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequestMapping("/board")
@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 질문 등록
    @PostMapping
    public ResponseEntity createQuestion(@RequestBody QuestionPostDto questionPostDto){

        Question question = Question.of(questionPostDto);
        boardService.saveQuestion(question);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateQuestion(@RequestBody QuestionPutDto questionPutDto) throws AccessDeniedException {

        Question question = Question.of(questionPutDto);
        boardService.updateQuestion(question);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable long questionId) throws AccessDeniedException {

        boardService.deleteQuestion(questionId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable long questionId) {



        //결국 서비스 단에서 DTO도 처리해주는게 좋을 거 같다.
        QuestionResponseDto response = boardService.findQuestion(questionId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/questions")
    public ResponseEntity getQuestions() {

        List<Question> questionList = boardService.findQuestions();
        //QuestionListResponseDto response=

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
