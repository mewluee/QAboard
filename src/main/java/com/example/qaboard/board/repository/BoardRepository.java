package com.example.qaboard.board.repository;

import com.example.qaboard.board.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Question, Long> {

}
