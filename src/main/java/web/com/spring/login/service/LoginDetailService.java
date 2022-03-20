package web.com.spring.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import web.com.spring.login.mapper.LoginMapper;
import web.com.spring.login.vo.LoginVO;
import web.com.spring.login.vo.UserDetail;

@Log4j2
@Service
@AllArgsConstructor
public class LoginDetailService implements UserDetailsService{
	
	private LoginMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LoginVO vo  = null;
		try {
			vo = mapper.getuser(vo);
			log.info( username, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new UserDetail(vo);
	}
}
