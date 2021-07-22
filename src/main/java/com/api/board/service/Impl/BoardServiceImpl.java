package com.api.board.service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.api.board.domain.Board;
import com.api.board.domain.BoardFile;
import com.api.board.mapper.BoardMapper;
import com.api.board.properties.FileProperties;
import com.api.board.service.BoardService;

@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private FileProperties fileProperties;
	
	@Autowired
	private BoardMapper boardMapper;

	/**
	 * 게시글 목록 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Board> getBoardList() throws Exception {
		return boardMapper.getBoardList();
	}

	/**
	 * 게시글 상세 조회
	 * 
	 * @param board_seq
	 * @return
	 * @throws Exception
	 */
	public Board getBoardDetail(int board_seq) throws Exception {
		return boardMapper.getBoardDetail(board_seq);
	};

	/**
     * 게시글 첨부파일 조회
     * 
     * @param board
     * @return
     * @throws Exception
     */
	public List<BoardFile> getBoardFileInfo(Board board, MultipartFile[] files) throws Exception {
		 
        List<BoardFile> boardFileList = new ArrayList<BoardFile>();
 
        BoardFile boardFile = new BoardFile();
 
        int boardSeq = board.getBoard_seq();
        String fileName = null;
        String fileExt = null;
        String fileNameKey = null;
        String fileSize = null;
        System.out.println("fileProperties.getUploadDirectory() : " + fileProperties.getUploadDirectory());
        // 파일이 저장될 Path 설정
        String filePath = fileProperties.getUploadDirectory();
        
        if (files != null && files.length > 0) {
 
            File file = new File(filePath);
            
            // 디렉토리가 없으면 생성
            if (file.exists() == false) {
                file.mkdirs();
            }
 
            for (MultipartFile multipartFile : files) {
 
                fileName = multipartFile.getOriginalFilename();
                fileExt = fileName.substring(fileName.lastIndexOf("."));
                // 파일명 변경(uuid로 암호화) + 확장자
                fileNameKey = getRandomString() + fileExt;
                fileSize = String.valueOf(multipartFile.getSize());
 
                // 설정한 Path에 파일 저장
                file = new File(filePath + "/" + fileNameKey);
 
                multipartFile.transferTo(file);
                System.out.println("fileName : " + fileName);
                boardFile = new BoardFile();
                boardFile.setBoard_seq(boardSeq);
                boardFile.setFile_name(fileName);
                boardFile.setFile_name_key(fileNameKey);
                boardFile.setFile_path(filePath);
                boardFile.setFile_size(fileSize);
                boardFileList.add(boardFile);
            }
        }
 
        return boardFileList;
	}
	 
	/**
	 * 게시글 등록
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int insertBoard(Board board) throws Exception {
		return  boardMapper.insertBoard(board);
	};
	
	/**
	 * 게시글 등록
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int insertBoardFiles(Board board, MultipartFile[] files) throws Exception {
		
		int insertCount = 0;
		
		insertCount = boardMapper.insertBoard(board);
		 
		List<BoardFile> boardFileList = getBoardFileInfo(board, files); 
		for (BoardFile boardFile : boardFileList) { 
			boardMapper.insertBoardFile(boardFile); 
		}
		
		return insertCount;
	};

	/**
	 * 게시글 수정
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateBoard(Board board) throws Exception {
		return boardMapper.updateBoard(board);
	};

	/**
	 * 게시글 삭제
	 * 
	 * @param board_seq
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int deleteBoard(int board_seq) throws Exception {
		return boardMapper.deleteBoard(board_seq);
	};
	
	/** 32글자의 랜덤한 문자열(숫자포함) 생성 */
    public static String getRandomString() { 
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
