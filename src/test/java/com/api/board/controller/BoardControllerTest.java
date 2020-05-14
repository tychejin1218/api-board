package com.api.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class BoardControllerTest {

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
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.alwaysDo(print())
			.build();
	}

	@Test
	public void getBoardList() throws Exception { // 게시글 목록 조회 테스트 - GET 방식

		String uri = "/board";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.accept(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 요청이 성공적으로 처리되었으면 테스트 통과
		assertEquals(200, status);
	}

	@Test
	public void getBoardDetail() throws Exception { // 게시글 상세 조회 테스트 - GET 방식

		String uri = "/board/1";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.accept(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 요청이 성공적으로 처리되었으면 테스트 통과
		assertEquals(200, status);
	}

	@Test
	public void insertBoard() throws Exception { // 게시글 등록 테스트 - POST 방식

		String uri = "/board";

		Board insertBoard = new Board();
		insertBoard.setBoard_writer("게시글 작성자 등록");
		insertBoard.setBoard_subject("게시글 제목 등록");
		insertBoard.setBoard_content("게시글 제목 등록");

		String inputJson = this.mapToJson(insertBoard);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(inputJson))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 요청이 성공적으로 처리되었으면 테스트 통과
		assertEquals(201, status);
	}

	@Test
	public void updateBoard() throws Exception { // 게시글 수정 테스트 - PUT 방식

		String uri = "/board/5";

		Board updateBoard = new Board();
		updateBoard.setBoard_seq(26);
		updateBoard.setBoard_subject("게시글 제목 수정");
		updateBoard.setBoard_content("게시글 제목 수정");

		String inputJson = this.mapToJson(updateBoard);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(inputJson))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 요청이 성공적으로 처리되었으면 테스트 통과
		assertEquals(200, status);
	}

	@Test
	public void deleteBoard() throws Exception { // 게시글 수정 테스트 - DELETE 방식

		String uri = "/board/5";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 요청이 성공적으로 처리되었으면 테스트 통과
		assertEquals(200, status);
	}
}