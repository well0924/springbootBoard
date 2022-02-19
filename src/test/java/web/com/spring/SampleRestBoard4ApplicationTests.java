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
	
	@Test
	public void test() throws Exception {
		BoardVO vo = new BoardVO();
		
		vo.setBoardTitle("insert test");
		vo.setBoardWriter("tester");
		vo.setBoardContents("test");
		vo.setCount(0);
		vo.setRegDate(null);
		
		int result = mapper.boardInsert(vo);
		System.out.println(result);
		System.out.println(vo);
	}
}
