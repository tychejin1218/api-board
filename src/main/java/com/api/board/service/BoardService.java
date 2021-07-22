package com.api.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.board.domain.Board;
import com.api.board.domain.BoardFile;

public interface BoardService {
	
	/**
	 * 게시글 목록 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Board> getBoardList() throws Exception; 
	
	/**
     * 게시글 상세 조회
     * 
     * @param board_seq
     * @return
     * @throws Exception
     */
    public Board getBoardDetail(int board_seq) throws Exception;
    
    /**
     * 게시글 첨부파일 조회
     * 
     * @param board
     * @return
     * @throws Exception
     */
	 public List<BoardFile> getBoardFileInfo(Board board, MultipartFile[] files) throws Exception;
 
    /**
     * 게시글 등록
     * 
     * @param board
     * @return
     * @throws Exception
     */
    public int insertBoard(Board board) throws Exception;
    
    /**
     * 게시글 등록
     * 
     * @param board
     * @return
     * @throws Exception
     */
    public int insertBoardFiles(Board board, MultipartFile[] files) throws Exception;
 
    /**
     * 게시글 수정
     * 
     * @param board
     * @return
     * @throws Exception
     */
    public int updateBoard(Board board) throws Exception;
 
    /**
     * 게시글 삭제
     * 
     * @param board_seq
     * @return
     * @throws Exception
     */
    public int deleteBoard(int board_seq) throws Exception;
}
