package com.api.board.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.api.board.domain.Board;
import com.api.board.service.BoardServiceTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerFileTest {

	Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)).alwaysDo(print()).build();
	}
/*
	@Test
	public void insertBoardFile() throws Exception { // 게시글 등록 테스트 - POST 방식

		String uri = "/board/files";

		Board insertBoard = new Board();
		insertBoard.setBoard_writer("게시글 작성자 첨부파일");
		insertBoard.setBoard_subject("게시글 제목 첨부파일");
		insertBoard.setBoard_content("게시글 내용 첨부파일");

		String jsonBoard = this.mapToJson(insertBoard);

		MockMultipartFile file01 = new MockMultipartFile(getRandomString(), "sample01.png", "image/png", new FileInputStream("C:/upload/board1/"));
		MockMultipartFile file02 = new MockMultipartFile(getRandomString(), "sample02.png", "image/png", new FileInputStream("C:/upload/board1/"));

		MvcResult mvcResult = mockMvc.perform(multipart(uri)
											 .file(file01)
											 .file(file02)
											 .param("board", jsonBoard)
											 .contentType(MediaType.MULTIPART_FORM_DATA)
											 .accept(MediaType.APPLICATION_JSON))
									 .andReturn();
		
		assertEquals(201, mvcResult.getResponse().getStatus());
	}*/
	
	/** 32글자의 랜덤한 문자열(숫자포함) 생성 */
    public static String getRandomString() { 
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}