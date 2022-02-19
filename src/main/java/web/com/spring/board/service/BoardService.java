package web.com.spring.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import web.com.spring.board.mapper.BoaradMapper;
import web.com.spring.board.vo.BoardVO;

@Service
@AllArgsConstructor
public class BoardService {
	
	private BoaradMapper mapper;
	
	//게시글 작성
	public int boardInsert(BoardVO vo) throws Exception{
		return mapper.boardInsert(vo);
	};
	//게시글 목록
	public List<BoardVO> boardlist() throws Exception{
		return mapper.boardlist();
	};
	//게시글 목록 조회
	public BoardVO detailBoard(Integer boardId)throws Exception{
		return mapper.detailBoard(boardId);
	};
	//게시글 수정
	public int boardUpdate(BoardVO vo)throws Exception{
		return mapper.boardUpdate(vo);
	};
	//게시글 수정
	public int boardDelete(Integer boardId)throws Exception{
		return mapper.boardDelete(boardId);
	};
}
