package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet(Model model) {
		model.addAttribute("page", "board");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public void writePost(String images[], String title, String content) {
		for (int i = 0; i < images.length; i++) {
			System.out.println(images[i]);
		}
		
		System.out.println(title);
		System.out.println(content);
	}
	
}
