package web.com.spring.login.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import web.com.spring.login.vo.LoginDto;
import web.com.spring.login.vo.LoginVO;

@Mapper
public interface LoginMapper {
	
	//회원가입
	public void join(LoginVO vo)throws Exception;
	//로그인,로그아웃
	public LoginVO loginproc(LoginDto vo)throws Exception;
	//회원중복체크
	public int IdCheck(String userid)throws Exception;
	//회원목록
	public List<LoginVO>memberlist()throws Exception;
	//회원 수정
	public int memberUpdate(String userid)throws Exception;
	//회원 삭제
	public void memberDelete(String userid)throws Exception;
	//회원아이디찾기
	public void searchId(String userid)throws Exception;
	//회원아이디 찾기
	public LoginVO searchId(LoginVO vo)throws Exception;
	//회원비밀번호 찾기
	public LoginVO searchPw(LoginVO vo)throws Exception;
	
}
