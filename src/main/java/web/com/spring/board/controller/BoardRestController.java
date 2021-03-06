package web.com.spring.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.board.service.BoardService;
import web.com.spring.board.vo.BoardDto;
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
		ResponseEntity<List<BoardVO>>entity = null;
		List<BoardVO>listboard = service.boardlist(cri);
		try {
			entity = new ResponseEntity<List<BoardVO>>(listboard,HttpStatus.OK);
			log.info("목록:"+listboard);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<BoardVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//게시글 작성 c.o
	@PostMapping("/board")
	public ResponseEntity<String>boardwrite(@ModelAttribute BoardDto.Request vo)throws Exception{
		ResponseEntity<String> entity = null;
		
		try {
			 int result = service.boardInsert(vo);
		 	 log.info("글삽입여부?:"+result);//1이면 성공!
			 entity = new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			 e.printStackTrace();
			 entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//게시글 수정 c.o
	@PutMapping("/board/{id}")
	public ResponseEntity<String>boardUpdate(@PathVariable("id") Integer boardId,@ModelAttribute BoardDto.UpdateRequest vo)throws Exception{
		ResponseEntity<String>entity = null;
		try {
			int result = service.boardUpdate(vo, boardId);
			log.info("글수정여부?:"+result);
			entity = new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	//게시글 삭제 c.o
	@DeleteMapping("/board/{id}")
	public ResponseEntity<String>boardDelete(@PathVariable("id") Integer boardId)throws Exception{
		ResponseEntity<String> entity = null;
		try {
			int result = service.boardDelete(boardId);
			log.info("글삭제여부?:"+result);
			entity = new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	//선택삭제(불안정)->제약성 문제
	@DeleteMapping("/selectdelete")
	public List<String> selectDelete(@RequestBody List<String> boardIdArray)throws Exception{
		log.info("선택삭제",boardIdArray);
		service.SelectDelete(boardIdArray);
		return boardIdArray;
	}
	
	//다운로드 기능
	@GetMapping("/download")
	public ResponseEntity<Resource> filedown(@RequestParam Integer boardId)throws Exception{
		
		HttpHeaders header = new HttpHeaders();
		Resource res = null;
		log.info("다운로드 기능");
		try {
			BoardDto.Result vo = service.detailBoard(boardId);
			String filepath = vo.getFilepath();
			String fullpath = filepath + vo.getStoredfilename();
			String originfilename = vo.getOriginfilename();
			File targetFile = new File(fullpath);
			
			if(targetFile.exists()) {
				String mimeType = Files.probeContentType(Paths.get(targetFile.getAbsolutePath()));
				
				if(mimeType == null) {
					mimeType ="application/octet-stream";
				}
				
				res = new UrlResource(targetFile.toURI());
				
				String encodingFileName = URLEncoder.encode(originfilename, "UTF-8").replace("+","%20");
				
				header.set("Content-Disposition", "attachment;filename="+encodingFileName + ";filename*=UTF-8''" + encodingFileName);
				header.setCacheControl("no-cache");
				header.setContentType(MediaType.parseMediaType(mimeType));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		log.info("다운로드 기능완료");
		return new ResponseEntity<Resource>(res,header,HttpStatus.OK);
	}
}
