package web.com.spring;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import web.com.spring.board.service.BoardService;
import web.com.spring.board.vo.BoardVO;

@SpringBootTest
class SampleRestBoard4ApplicationTests {
	
	@Autowired
	private BoardService mapper;
	
	@Test
	void contextLoads() {
	}
	//글 작성 테스트
//	@Test
//	public void test() throws Exception {
//		BoardVO vo = new BoardVO();
//		
//		vo.setBoardTitle("insert test");
//		vo.setBoardWriter("tester");
//		vo.setBoardContents("test");
//		vo.setCount(0);
//		vo.setRegDate(null);
//		
//		int result = mapper.boardInsert(vo);
//		System.out.println(result);
//		System.out.println(vo);
//	}
//	@Test
//	public  void updatete() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setBoardId(2);
//		vo.setBoardTitle("수정테스트");
//		vo.setBoardWriter("테스트");
//		vo.setBoardContents("updateTest");
//		vo.setUpdateDate(null);
//		int result = mapper.boardUpdate(vo);
//		System.out.println("결과"+result);
//		System.out.println(vo);
//	}
	//글 삭제
	@Test
	public void deleteTest() throws Exception {
		BoardVO vo =  new BoardVO();
		int result = mapper.boardDelete(4);
		System.out.println("결과:"+result);
	}
}
