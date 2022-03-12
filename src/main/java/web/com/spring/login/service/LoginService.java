package web.com.spring.login.service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import web.com.spring.login.mapper.LoginMapper;
import web.com.spring.login.vo.LoginDto;
import web.com.spring.login.vo.LoginVO;

@Service
@AllArgsConstructor
public class LoginService{
	
	private LoginMapper mapper;
	
	//회원가입
	public void join(LoginVO vo)throws Exception{
		mapper.join(vo);
	};
	//로그인,로그아웃
	public LoginVO loginproc(LoginDto vo)throws Exception{
		return mapper.loginproc(vo);
	};
	//회원중복체크
	public int IdCheck(String userid)throws Exception{
		return mapper.IdCheck(userid);
	};
	//회원목록
	public List<LoginVO>memberlist()throws Exception{
		return mapper.memberlist();
	};
	//회원 수정
	public int memberUpdate(String userid)throws Exception{
		return mapper.memberUpdate(userid);
	};
	//회원 삭제
	public void memberDelete(String userid)throws Exception{
		mapper.memberDelete(userid);
	};
	
}
