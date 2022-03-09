package web.com.spring.board.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.com.spring.board.mapper.BoardMapper;
import web.com.spring.board.vo.BoardDto;
import web.com.spring.board.vo.BoardVO;
import web.com.spring.common.Criteria;

@Service
public class BoardService {
	
	private BoardMapper mapper;
	//생성자를 이용해서 빈을 주입
	public BoardService(BoardMapper mapper) {
		 this.mapper = mapper;
	}
	//파일 경로
	@Value("${external.files.path}")
	private String filepath;

	//게시글 목록
	public List<BoardVO> boardlist(Criteria cri) throws Exception{
		return mapper.boardlist(cri);
	};
	//게시글 작성
	public int boardInsert(BoardDto.Request vo) throws Exception{
		
		MultipartFile file = vo.getFile();
		
		String originfilename = "";
		String ext = "";
		String storedfilename="";
		
		if(!file.isEmpty()) {
			originfilename = file.getOriginalFilename();
			ext = originfilename.substring(originfilename.lastIndexOf(".")+1);
			storedfilename = "file"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+"."+ext;
			
			this.createFile(file, originfilename, storedfilename);
		}
		
		vo.setOriginfilename(originfilename);
		vo.setStoredfilename(storedfilename);
		vo.setFilepath(filepath);
		return mapper.boardInsert(vo);
	};
	//게시글 목록 조회
	public BoardDto.Result detailBoard(Integer boardId)throws Exception{
		return mapper.detailBoard(boardId);
	};
	//게시글 수정
	public int boardUpdate(BoardDto.UpdateRequest vo,Integer boardId)throws Exception{
		BoardDto.Result detail = mapper.detailBoard(boardId);
		return mapper.boardUpdate(vo);
	};
	//게시글 선택삭제
	public void SelectDelete(List<String> boardIdArray)throws Exception{
		for(int i=0;i<boardIdArray.size();i++) {
			mapper.SelectDelte(boardIdArray.get(i));
		}
	}
	//게시글 수정
	public int boardDelete(Integer boardId)throws Exception{
		return mapper.boardDelete(boardId);
	};
	//조회수 증가
	public void countup(Integer boardId)throws Exception{
		 mapper.countup(boardId);
	};
	//게시글 총갯수
	public int countsum(Criteria cri)throws Exception{
		return mapper.countsum(cri);
	};
	
	private void createFile(MultipartFile file, String originfilename,String storedfilename)throws Exception{
		String fullpath = "";
		
		fullpath = filepath + storedfilename;
		
		File newFile  = new File(fullpath);
		
		if(!newFile.exists()) {
			if(newFile.getParentFile().mkdir()) {
				newFile.createNewFile();
			}
		}
		file.transferTo(newFile);
	}
}
