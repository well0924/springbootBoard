package web.com.spring.reply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import web.com.spring.reply.vo.ReplyVO;

@Mapper
public interface ReplyMapper {
	//댓글 목록 o.k
	public List<ReplyVO> list(int bno) throws Exception;
	//댓글  삽입 o.k
	public int InsertReply(ReplyVO vo)throws Exception;
	//댓글 수정 
	public int ReplyUpdate(ReplyVO vo)throws Exception;
	//댓글 삭제 o.k
	public int DeleteReply(Integer rno)throws Exception;
}
