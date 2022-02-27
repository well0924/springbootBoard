package web.com.spring.reply.controller;

import java.sql.SQLException;
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
import web.com.spring.reply.service.ReplyService;
import web.com.spring.reply.vo.ReplyVO;

@Slf4j
@RestController
@RequestMapping("/reply")
@AllArgsConstructor
public class ReplyController {
	
	private final ReplyService service;
	
	//댓글 목록(기능 o.k)
	@GetMapping("/{bno}")
	public ResponseEntity<List<ReplyVO>>replylist(@PathVariable("bno") int bno)throws Exception{
		ResponseEntity<List<ReplyVO>> entity = null;
		List<ReplyVO>list = service.list(bno);
		try {
			entity = new ResponseEntity<List<ReplyVO>>(list,HttpStatus.OK);
			log.info("댓글 목록:"+list);
		} catch (Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}	
	//댓글 작성 (기능 o.k)
	@PostMapping("/write")
	public ResponseEntity<String>writeReply(@RequestBody ReplyVO vo)throws Exception,SQLException{
		ResponseEntity<String> entity = null;
		try {
			int result=service.InsertReply(vo);
			log.info("글삽입 여부:"+result);//1이면 정상
			entity = new ResponseEntity<String>("Success",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("Bed",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	//댓글 수정 기능 o.k
	@PutMapping("/{bno}/{rno}")
	public ResponseEntity<String>updateReply(@RequestBody ReplyVO vo, @PathVariable("bno")int bno,@PathVariable("rno")int rno)throws Exception,SQLException{
		ResponseEntity<String> entity = null;
		try {
			int result = service.ReplyUpdate(vo);
			log.info("글수정여부?:"+result);
			entity = new ResponseEntity<String>("Success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity =new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	//댓글 삭제 (기능 o.k)
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<Integer>deleteReply(@PathVariable("bno")int bno,@PathVariable("rno")int rno)throws Exception,SQLException{
		ResponseEntity<Integer> entity = null;
		try {
			int result = service.DeleteReply(rno);
			log.info("글삭제:"+result);//1이면 정상
			entity = new ResponseEntity<Integer>(HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity =new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return  entity;
	}
}
