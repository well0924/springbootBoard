package web.com.spring.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	//서비스
	private final LoginService service;	
	//암호화 
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//이메일
	@Autowired
	private JavaMailSender mailsender;
	
	//로그인 페이지 o.k
	@GetMapping("/page")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		log.info("로그인페이지");
		mv.setViewName("/login/loginpage");
		return mv;
	}
	
	//회원가입 페이지 o.k
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
	
	//회원중복 기능 o.k
	@ResponseBody
	@PostMapping("/idCheck")
	public Map<Object,Object>idCheck(@RequestBody String userid)throws Exception{
		Map<Object,Object>result = new HashMap<>();
		log.info("아이디 중복체크기능");
		try {
			int count = service.IdCheck(userid);
			result.put("userid", count);
			log.info("중복?:"+count);//1이면 중복
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//이메일 인증 전송기능 o.k
	@ResponseBody
	@GetMapping("/mailCheck")
	public String emailCheck(@RequestParam String userEmail)throws Exception{
		log.info("이메일 전송 확인");
		log.info("메일:"+ userEmail);
		
		Random random = new Random();
		int checkNum = random.nextInt(88888)+11111;
		log.info("인증번호:"+checkNum);
		
		String setFrom = "rayman0924@naver.com";
		String toMail = userEmail;
		String title = "회원가입 인증 이메일 입니다.";
		String content = 
				"홈페이지를 방문해주셔서 감사합니다." +
				"<br><br>" + 
				"인증 번호는 " + checkNum + "입니다." + "<br>" + 
				"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		try {
				MimeMessage message = mailsender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content,true);
				mailsender.send(message);
		}	catch (Exception e) {
			e.printStackTrace();
		}
		
		String num = Integer.toString(checkNum);
		
		return num;	
	}
	
	//회원가입기능 o.k
	@PostMapping("/joinproc")
	public String loginProc(LoginVO vo)throws Exception{
		log.info("회원가입 기능");
		String userpw = "";
		String encode = "";
		
		userpw = vo.getUserPw();
		encode = encoder.encode(userpw);
		vo.setUserPw(encode);
		service.join(vo);
		
		return "/login/joinsuccess";
	}
	
	//로그인기능
	@PostMapping("/loginproc")
	public String loginProc(LoginVO vo, RedirectAttributes redirect, HttpServletRequest req)throws Exception{
		HttpSession session = req.getSession();
		LoginVO login = service.getuser(vo);
		log.info("로그인 기능");
		
		String userpw = "";
		String encodepw = "";
		
		userpw = vo.getUserPw();
		encodepw = login.getUserPw();
		
		log.info(encodepw);
		
		boolean matcher = encoder.matches(userpw, encodepw);
		
		if(login != null && matcher ==true) {//로그인이 된 경우
			session.setAttribute("login", login);
			System.out.println(login);
			return "/login/successpage";
		}else{//로그인이 안되는 경우
			session.setAttribute("login", null);
			redirect.addFlashAttribute("loginfail",false);
			return "redirect:/login/page";
		}
	}
	
	//로그아웃 기능
	@GetMapping("/logoutproc")
	public String logoutProc(HttpServletRequest req)throws Exception{
		log.info("로그아웃기능");
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login")!=null) {
			session.removeAttribute("login");
			session.invalidate();
		}
		return "redirect:/board/list";
	}
	
	//회원목록 출력하기
	@ResponseBody
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
	@PutMapping("/updatemember/{id}")
	public Map<Object, Object> UpdateMember(@PathVariable(value="id") String userId)throws Exception{
		log.info("회원수정기능");
		Map<Object,Object> result = new HashMap<>();
		try {
			result.put("id", userId);
			int count = service.memberUpdate(userId);
			if(count >0) {
				result.put("CommonO.k", 200);
			}else {
				result.put("Fail", 400);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	//회원삭제 및 탈퇴
	@DeleteMapping("/deletemember/{id}")
	public void DeleteMember(@PathVariable(value="id") String userId)throws Exception{
		log.info("회원탈퇴 및 삭제기능");
		try {
			service.memberDelete(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
