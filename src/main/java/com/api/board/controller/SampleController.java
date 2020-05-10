package com.api.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/sample")
@Controller
public class SampleController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getSample() {
		return "sample1";
	}
}