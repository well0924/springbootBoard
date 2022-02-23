package web.com.spring.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import web.com.spring.board.vo.BoardVO;
import web.com.spring.common.Criteria;

@Mapper
public interface BoardMapper {
	
	//게시글 작성 o.k
	public int boardInsert(BoardVO vo) throws Exception;
	//게시글 목록 o.k
	public List<BoardVO> boardlist(Criteria cri) throws Exception; 
	//게시글 목록 조회 o.k
	public BoardVO detailBoard(Integer boardId)throws Exception;
	//게시글 수정 o.k
	public int boardUpdate(BoardVO vo)throws Exception;
	//게시글 삭제 o.k
	public int boardDelete(Integer boardId)throws Exception;
	//게시글 선택 삭제 
	public void SelectDelte(String boardId)throws Exception;
	//조회수 증가 o.k
	public void countup(Integer boardId)throws Exception;
	//게시글 총갯수 o.k
	public int countsum(Criteria cri)throws Exception;
}
