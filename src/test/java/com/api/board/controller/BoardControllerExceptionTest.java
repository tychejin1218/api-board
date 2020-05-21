package com.api.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.api.board.service.BoardServiceTest;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerExceptionTest {

	Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.alwaysDo(print())
			.build();
	}

	@Test
	public void getBoardDetailJSON() throws Exception { // 게시글 상세 조회 테스트 - GET 방식

		String uri = "/board/100";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 404가 응답되면 테스트 통과
		assertEquals(404, status);
	}

	@Test
	public void getBoardDetailXML() throws Exception { // 게시글 상세 조회 테스트 - GET 방식

		String uri = "/board/100";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
			.accept(MediaType.APPLICATION_XML))
			.andReturn();

		int status = mvcResult.getResponse()
			.getStatus();

		// 404가 응답되면 테스트 통과
		assertEquals(404, status);
	}
}