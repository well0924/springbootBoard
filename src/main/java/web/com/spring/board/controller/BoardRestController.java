package web.com.spring.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.board.service.BoardService;
import web.com.spring.board.vo.BoardVO;
import web.com.spring.common.Criteria;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/*")
public class BoardRestController {
	
	private final BoardService service;
	
	//게시글 목록 c.o
	@GetMapping("/list")
	public ResponseEntity<List<BoardVO>>boardList(Criteria cri)throws Exception{
		List<BoardVO>listboard = new ArrayList<>();
		listboard = service.boardlist(cri);
		return new ResponseEntity<>(listboard,HttpStatus.OK);
	}
	
	//게시글 작성 c.o
	@PostMapping(value="/board")
	public ResponseEntity<String>boardwrite(@RequestBody BoardVO vo)throws Exception{
		int result = service.boardInsert(vo);
		log.info("글삭제여부?:"+result);
		return result == 1 ? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//게시글 수정 c.o
	@PutMapping("/board/{id}")
	public ResponseEntity<String>boardUpdate(@PathVariable("id") Integer boardId,@RequestBody BoardVO vo)throws Exception{
		int result = service.boardUpdate(vo);
		log.info("글삭제여부?:"+result);
		return result ==1 ? new ResponseEntity<>(HttpStatus.OK): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//게시글 삭제 c.o
	@DeleteMapping("/board/{id}")
	public ResponseEntity<String>boardDelete(@PathVariable("id") Integer boardId)throws Exception{
		int result = service.boardDelete(boardId);
		log.info("글삭제여부?:"+result);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
