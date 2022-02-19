package web.com.spring.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import web.com.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	//게시글 작성
	public int boardInsert(BoardVO vo) throws Exception;
	//게시글 목록
	public List<BoardVO> boardlist() throws Exception; 
	//게시글 목록 조회
	public BoardVO detailBoard(Integer boardId)throws Exception;
	//게시글 수정
	public int boardUpdate(BoardVO vo)throws Exception;
	//게시글 수정
	public int boardDelete(Integer boardId)throws Exception;

}
