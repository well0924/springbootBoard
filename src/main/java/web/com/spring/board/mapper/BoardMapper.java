package web.com.spring.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import web.com.spring.board.vo.BoardDto;
import web.com.spring.board.vo.BoardVO;
import web.com.spring.common.Criteria;

@Mapper
public interface BoardMapper {
	
	//게시글 작성(파일첨부) o.k
	public int boardInsert(BoardDto.Request vo) throws Exception;
	//게시글 목록 o.k
	public List<BoardVO> boardlist(Criteria cri) throws Exception; 
	//게시글 목록 조회 o.k
	public BoardDto.Result detailBoard(Integer boardId)throws Exception;
	//게시글 수정(파일첨부) o.k
	public int boardUpdate(BoardDto.UpdateRequest vo)throws Exception;
	//게시글 삭제 (파일첨부)o.k
	public int boardDelete(Integer boardId)throws Exception;
	//게시글 선택 삭제 o.k
	public int SelectDelte(String boardId)throws Exception;
	//조회수 증가 o.k
	public void countup(Integer boardId)throws Exception;
	//게시글 총갯수 o.k
	public int countsum(Criteria cri)throws Exception;
}
