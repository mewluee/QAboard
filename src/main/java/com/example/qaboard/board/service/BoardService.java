package com.example.qaboard.board.service;

import com.example.qaboard.board.dto.QuestionResponseDto;
import com.example.qaboard.board.entity.Question;
import com.example.qaboard.board.repository.BoardRepository;
import com.example.qaboard.member.repository.MemberRepository;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public void saveQuestion(Question question){

        System.out.println("log: service > saveQuestion");

        boardRepository.save(question);

    }

    @Transactional
    public void updateQuestion(Question question) throws AccessDeniedException {

        System.out.println("log: service > updateQuestion");

        //해당 글을 작성한 멤버인지 확인 필요
        Question finded=boardRepository.findById(question.getQuestionId()).orElseThrow(NullPointerException::new);
        if (finded.getMemberId() != question.getMemberId()) {
            //글작성 멤버가 아니다. 솔직히 좀 억지다..세큐리티해야할거같은데
            throw new AccessDeniedException("작성자가 아닙니다.");
        }else{
            finded.setTitle(question.getTitle());
            finded.setContent(question.getContent());
            finded.setPostStatus(question.getPostStatus());

        }

    }

    public void deleteQuestion(long questionId) {

        System.out.println("log: service > deleteQuestion");

        //이거......memberid를...url로 하면 안되서..사실 지금 막을수가없음... 위엔 그냥 DTO로 받아서 어찌저찌했는데.. 여긴 세큐리티 파야할거같은데..
//        Question finded = boardRepository.findById(questionId).orElseThrow(NullPointerException::new);
//        if(finded.)

        boardRepository.deleteById(questionId);
    }

    public QuestionResponseDto findQuestion(long questionId) {

        System.out.println("log: service > findQuestion");

        // 문구는 어떻게 못보내나?
        // 이거 나중에 AOP로 NullPointerException일때 일정 문구 출력하게 처리하기.
        Question question= boardRepository.findById(questionId).orElseThrow(NullPointerException::new);
        // findById의 리턴값이 entity 혹은 Optional(empty)를 반환한다.
        // orElseThrow는
        String name = memberRepository.findById(question.getMemberId()).orElseThrow(NullPointerException::new).getName();
        QuestionResponseDto responseDto = QuestionResponseDto.of(question, name);

        return responseDto;
    }

    public List<Question> findQuestions() {

        System.out.println("log: service > findQuestions(list)");

        List<Question> questions = boardRepository.findAll();
        //List에는 null이어도 괜찮나보네..왜? 이유 찾아보기.
        //

        return questions;
    }
}
