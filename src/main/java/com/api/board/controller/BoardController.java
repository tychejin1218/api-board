package com.api.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.board.domain.Board;
import com.api.board.domain.Boards;
import com.api.board.exception.ResourceNotFoundException;
import com.api.board.service.BoardService;

@RequestMapping(value = "/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	/**
	 * 게시글 목록 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Boards getBoardList() throws Exception {

		Boards boards = new Boards();
		boards.setBoards(boardService.getBoardList());

		return boards;
	}

	/**
	 * 게시글 상세 조회
	 * 
	 * @param board_seq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{board_seq}", method = RequestMethod.GET)
	@ResponseBody
	public Board getBoardDetail(@PathVariable("board_seq") int board_seq) throws Exception {

		Board board = boardService.getBoardDetail(board_seq);

		if (board == null) {
			throw new ResourceNotFoundException();
		}

		return board;
	}

	/**
	 * 게시글 등록
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public Board insertBoard(@RequestBody Board board) throws Exception {

		boardService.insertBoard(board);

		int boardSeq = board.getBoard_seq();

		Board boardDetail = boardService.getBoardDetail(boardSeq);

		return boardDetail;
	}

	/**
	 * 게시글 수정
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{board_seq}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Board updateBoard(@PathVariable("board_seq") int board_seq, @RequestBody Board board) throws Exception {

		boardService.updateBoard(board);

		Board boardDetail = boardService.getBoardDetail(board_seq);

		return boardDetail;
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param board_seq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{board_seq}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Board deleteBoard(@PathVariable("board_seq") int board_seq) throws Exception {

		boardService.deleteBoard(board_seq);

		Board deleteBoard = new Board();
		deleteBoard.setBoard_seq(board_seq);

		return deleteBoard;
	}
}