package com.api.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/sample")
@Controller
public class SampleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getSample() {
		return "sample";
	}
}