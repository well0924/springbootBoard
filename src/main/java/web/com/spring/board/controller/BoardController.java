package web.com.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import web.com.spring.board.service.BoardService;

@Controller
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/index");
		return mv;
	}
}
