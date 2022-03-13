package web.com.spring.login.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetail extends User{
	
	private static final long serialVersionUID = 1L;
	
	private static final String Rolefix = "ROLE_";
	
	public UserDetail(LoginVO vo) {
		super(vo.getUserid(),vo.getUserpw(),makeGrantedAuthorities(vo.getRolename()));
	}
	//권한 부여 메서드
	private static List<GrantedAuthority> makeGrantedAuthorities(String rolename) {
			
		List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(Rolefix + rolename));
        return list;
	}
}
