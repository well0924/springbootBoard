package web.com.spring.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import web.com.spring.login.vo.LoginVO;

@Mapper
public interface LoginMapper {
	
	//회원가입
	public void join(LoginVO vo)throws Exception;
	//로그인,로그아웃
	public LoginVO loginproc(LoginVO vo)throws Exception;
	//회원중복체크
	public int IdCheck(String userid)throws Exception;
	//회원목록
	public List<LoginVO>memberlist()throws Exception;
	//회원 수정
	public int memberUpdate(String userid)throws Exception;
	//회원 삭제
	public void memberDelete(String userid)throws Exception;
}
