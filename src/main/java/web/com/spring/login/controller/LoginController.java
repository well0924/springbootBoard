package web.com.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.login.service.LoginService;

@Slf4j
@Controller
@RequestMapping("/login/*")
@AllArgsConstructor
public class LoginController {
	
	private LoginService service;
	
	@GetMapping("/page")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		log.info("로그인페이지");
		mv.setViewName("/login/loginpage");
		return mv;
	}
	
	
}
