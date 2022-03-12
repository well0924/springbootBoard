package web.com.spring.login.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginVO {
	
	private String userid;
	private String userpw;
	private String username;
	private String userphone;
	private String useremail;
	private String useraddr1;
	private String useraddr2;
	private String useraddr3;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime userdate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime userupdate;
}
