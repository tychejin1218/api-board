package com.api.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.board.domain.Board;
import com.api.board.service.BoardService;

@RequestMapping(value = "/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Board> getBoardList() throws Exception {

		List<Board> boardList = boardService.getBoardList();

		return boardList;
	}
}