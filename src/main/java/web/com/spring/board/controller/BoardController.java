package web.com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.board.service.BoardService;
import web.com.spring.board.vo.BoardVO;
import web.com.spring.common.Criteria;
import web.com.spring.common.pageinfo;

@Slf4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	//메인 페이지
	@GetMapping("/main")
	public ModelAndView main() {
		log.info("main page");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/index");
		return mv;
	}
	//about페이지
	@GetMapping("/about")
	public ModelAndView AboutPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/main/about");
		return mv;
	}
	//글목록 화면
	@GetMapping("/list")
	public ModelAndView boardListPage(
			@RequestParam(required = false, defaultValue = "T") String searchType,
			@RequestParam(required = false) String keyword,
			Criteria cri)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		List<BoardVO> list= null;
		
		list = service.boardlist(cri);
		int articlelist = service.countsum(cri);
		// 페이징 객체(검색)
		pageinfo paging = new pageinfo();
		paging.setCri(cri);
		paging.setTotalCount(articlelist);
		paging.setKeyword(keyword);
		//데이터 바인딩
		//페이징 처리
		mv.addObject("paging", paging);
		//데이터 총갯수
		mv.addObject("count", articlelist);
		//게시글 
		mv.addObject("boardList", list);	
		//화면 설정
		mv.setViewName("board/list");
		
		return mv;
	}
	//글작성화면
	@GetMapping("/writeForm")
	public ModelAndView boardWritePage()throws Exception{
		ModelAndView mv = new ModelAndView();
		log.info("작성 페이지");
		mv.setViewName("board/write");
		return mv;
	}
	
	//글 조회화면
	@GetMapping("/detailForm/{boardid}")
	public ModelAndView boardReForm(@PathVariable("boardid") Integer boardId,Criteria cri)throws Exception{
		ModelAndView mv = new ModelAndView();
		// 페이징 객체(검색)
		pageinfo paging = new pageinfo();
		paging.setCri(cri);
				
		BoardVO vo =null;
		vo = service.detailBoard(boardId);
		//조회수 증가 기능 c.o
		service.countup(boardId);

		mv.addObject("paging", paging);
		mv.addObject("detail", vo);
		mv.setViewName("board/detail");
		return mv;
	}
	
	//선택삭제(불안정)
	@PostMapping("/selectdelete")
	public String selectDelete(HttpServletRequest req)throws Exception{
		log.info("선택삭제");
		String[] ajaxMsg = req.getParameterValues("valueArr");
		log.info("????:"+ajaxMsg);
		int size = ajaxMsg.length;
		log.info("길이:"+size);
		for(int i =0; i<size;i++) {
			service.SelectDelete(ajaxMsg[i]);
		}
		return "redirect:/board/list";
	}
}
