package web.com.spring.reply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import web.com.spring.reply.mapper.ReplyMapper;
import web.com.spring.reply.vo.ReplyVO;

@Service
@AllArgsConstructor
public class ReplyService {
	
	private ReplyMapper mapper;
	
	//댓글 목록
	public List<ReplyVO> list(int bno) throws Exception{
		return mapper.list(bno);
	};
	//댓글  삽입
	public int InsertReply(ReplyVO vo)throws Exception{
		return mapper.InsertReply(vo);
	};
	//댓글 수정
	public int ReplyUpdate(ReplyVO vo)throws Exception{
		return mapper.ReplyUpdate(vo);
	};
	//댓글 삭제
	public int DeleteReply(ReplyVO vo)throws Exception{
	     return	mapper.DeleteReply(vo);
	};
}
