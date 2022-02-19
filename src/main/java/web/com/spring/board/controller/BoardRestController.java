package web.com.spring.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	//게시글 목록
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
		return new ResponseEntity<>(listboard,HttpStatus.OK);
	}
}
