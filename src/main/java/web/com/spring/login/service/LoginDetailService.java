package web.com.spring.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import web.com.spring.login.mapper.LoginMapper;
import web.com.spring.login.vo.LoginVO;
import web.com.spring.login.vo.UserDetail;

@Service
@AllArgsConstructor
public class LoginDetailService implements UserDetailsService{
	
	private LoginMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginVO vo  = null;
		try {
			vo = mapper.loginproc(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new UserDetail(vo);
	}
}
