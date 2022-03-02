package web.com.spring.login.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import web.com.spring.login.mapper.LoginMapper;

@Service
@AllArgsConstructor
public class LoginService {
	
	private LoginMapper mapper;
}
