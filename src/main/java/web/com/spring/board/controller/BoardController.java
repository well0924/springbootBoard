package web.com.spring.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.board.service.BoardService;
import web.com.spring.board.vo.BoardVO;

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
	//글목록 화면
	@GetMapping("/list")
	public ModelAndView boardListPage()throws Exception{
		log.info("목록화면");
		ModelAndView mv = new ModelAndView();
		List<BoardVO> list= null;
		try {
			list = service.boardlist();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//데이터 바인딩
		mv.addObject("boardList", list);
		log.info("....list outprinting");
		//화면 설정
		mv.setViewName("board/list");
		
		return mv;
	}
	//글작성화면
	@GetMapping("/writeForm")
	public ModelAndView boardWritePage()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/write");
		return mv;
	}
	
	//글 조회화면
	@GetMapping("/rewriteForm")
	public ModelAndView boardReForm()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/detail");
		return mv;
	}
}
