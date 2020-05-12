package com.api.board.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.board.domain.Board;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardMapperTest {

	Logger logger = LoggerFactory.getLogger(BoardMapperTest.class);

	@Autowired
	BoardMapper boardMapper;
	
	@Test
	public void testA() { // 게시글 목록 조회 테스트
		try {
			List<Board> bookList = boardMapper.getBoardList();
			
			// 게시글 5건일 경우 테스트 통과
			assertEquals(5, bookList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testB() { // 게시글 상세 조회 테스트

		try {
			
			Board bookDetail = boardMapper.getBoardDetail(1);
			 
			// 게시글 상세 조회 정보가 있으면 테스트 통과
			assertNotNull(bookDetail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testC() { // 게시글 등록 테스트

		try {

			Board board = new Board();
			board.setBoard_writer("게시글 작성자6");
			board.setBoard_subject("게시글 제목6");
			board.setBoard_content("게시글 제목6");
			
			boardMapper.insertBoard(board);

			List<Board> bookList = boardMapper.getBoardList();
			
			// 게시글 6건일 경우 경우 테스트 통과
			assertEquals(6, bookList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testD() { // 게시글 수정 테스트

		try {

			Board board = new Board();
			board.setBoard_seq(6);
			board.setBoard_subject("게시글 제목7");
			board.setBoard_content("게시글 제목7");
			
			boardMapper.updateBoard(board);

			Board boardDetail = boardMapper.getBoardDetail(6);
			
			// 게시글 제목이 수정된 경우 테스트 통과
			assertEquals("게시글 제목7", boardDetail.getBoard_subject());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testE() { // 게시글 삭제 테스트
		
		try {
			
			boardMapper.deleteBoard(6);

			Board boardDetail = boardMapper.getBoardDetail(6);
			
			// 삭제한 게시글이 조회되지 않으면 테스트 통과
			assertNull(boardDetail);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
