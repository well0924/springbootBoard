package web.com.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.board.service.BoardService;

@Slf4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/")
	public ModelAndView main() {
		log.info("main page");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/index");
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView boardListPage()throws Exception{
		log.info("목록화면");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		return mv;
	}
}
