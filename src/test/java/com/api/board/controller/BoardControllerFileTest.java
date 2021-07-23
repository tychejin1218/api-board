package com.api.board.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

	@Test
	public void insertBoardFile() throws Exception { // 게시글 등록 테스트 - POST 방식

		Resource fileResource01 = new ClassPathResource("image/sample/spring1.png");
		Resource fileResource02 = new ClassPathResource("image/sample/springboot.png");
		
		if(fileResource01.isFile() && fileResource02.isFile()) {
		
			Board insertBoard = new Board();
			insertBoard.setBoard_writer("게시글 작성자 첨부파일");
			insertBoard.setBoard_subject("게시글 제목 첨부파일");
			insertBoard.setBoard_content("게시글 내용 첨부파일");
			
			MockMultipartFile file01 = new MockMultipartFile("files", fileResource01.getFilename(), MediaType.MULTIPART_FORM_DATA_VALUE, fileResource01.getInputStream());
			MockMultipartFile file02 = new MockMultipartFile("files", fileResource02.getFilename(), MediaType.MULTIPART_FORM_DATA_VALUE, fileResource02.getInputStream());
			
			MvcResult mvcResult = mockMvc.perform(multipart("/board/files")
					.file(file01)
					.file(file02)
					.param("board", this.mapToJson(insertBoard))
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();
			
			assertEquals(201, mvcResult.getResponse().getStatus());
		}
	}
}