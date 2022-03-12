package web.com.spring.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.com.spring.login.service.LoginService;
import web.com.spring.login.vo.LoginVO;

@Slf4j
@Controller
@RequestMapping("/login/*")
@AllArgsConstructor
public class LoginController {
	
	private final LoginService service;
	
	//암호화 
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//로그인 페이지
	@GetMapping("/page")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		log.info("로그인페이지");
		mv.setViewName("/login/loginpage");
		return mv;
	}
	
	//회원가입화면
	@GetMapping("/join")
	public ModelAndView joinPage() {
		ModelAndView mv = new ModelAndView();
		log.info("회원가입페이지");
		mv.setViewName("/login/join");
		return mv;
	}
	//아이디 비밀번호 찾기 페이지
	@GetMapping("/idpwsearch")
	public ModelAndView idpwSearch() {
		ModelAndView mv = new ModelAndView();
		log.info("아이디 및 비밀번호 찾기");
		mv.setViewName("/login/search");
		return mv;
	}
	//아이디 비밀번호 찾기 기능
	//회원중복 기능
	@PostMapping("/idCheck")
	@ResponseBody
	public Map<Object,Object>idCheck(@RequestBody String userid)throws Exception{
		Map<Object,Object>result = new HashMap<>();
		log.info("아이디 중복체크기능");
		try {
			int count = service.IdCheck(userid);
			result.put("userid", count);
			log.info("중복?:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//이메일 전송기능
	
	//회원가입기능
	@PostMapping("/joinproc")
	public String loginProc(LoginVO vo)throws Exception{
		log.info("회원가입 기능");
		String userpw = "";
		String encodepw = "";
		
		userpw = vo.getUserpw();
		encodepw = encoder.encode(userpw);
		vo.setUserpw(userpw);
		service.join(vo);
		
		return "/login/joinsuccess";
	}
	
	//로그인기능
	@PostMapping("/loginproc")
	public String loginProc(LoginVO vo,RedirectAttributes redirect, HttpServletRequest req)throws Exception{
		HttpSession session = req.getSession();
		LoginVO login = service.loginproc(vo);
		
		String userpw = "";
		String encodepw = "";
		
		userpw = vo.getUserpw();
		encodepw = login.getUserpw();
		boolean matcher = encoder.matches(userpw, encodepw);
		if(login != null && matcher ==true) {
			session.setAttribute("login", login);
			return "/board/list";
		}else{
			session.setAttribute("login", null);
			redirect.addFlashAttribute("loginfail",false);
			return "redirect:/login/page";
		}
	}
	
	//회원목록 출력하기
	@GetMapping("/memberlist")
	public List<LoginVO>memberlist()throws Exception{
		log.info("회원목록");
		List<LoginVO> member = null;
		try {
			member = service.memberlist();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return member;
	}
	//회원수정
	//회원삭제 및 탈퇴
	
}
