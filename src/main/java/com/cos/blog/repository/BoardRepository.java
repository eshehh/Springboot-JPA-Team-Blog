package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

//DAO
// 자동으로 bean 등록이 된다
// @Repository // 생략 가능하다

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
