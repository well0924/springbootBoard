package web.com.spring.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.board.service.BoardService;
import web.com.spring.board.vo.BoardVO;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/*")
public class BoardRestController {
	
	private BoardService service;
	
	//게시글 목록 c.o
	@GetMapping("/list")
	public ResponseEntity<List<BoardVO>>boardList()throws Exception{
		log.info("글 목록");
		List<BoardVO>listboard = new ArrayList<>();
		try {
			listboard = service.boardlist();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		log.info("msg", listboard);
		return new ResponseEntity<>(listboard,HttpStatus.OK);
	}
	
	//게시글 작성 c.o
	@PostMapping("/board")
	public ResponseEntity<String>boardwrite(@RequestBody BoardVO vo)throws Exception{
		log.info("글 작성");
		try {
			int result = service.boardInsert(vo);
			
			if(result >0) {
				System.out.println("o.k");
				
			}else {
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//게시글 수정
	@PutMapping("/board/{id}")
	public ResponseEntity<String>boardUpdate(@PathVariable("id") Integer boardId)throws Exception{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//게시글 삭제
	
}
