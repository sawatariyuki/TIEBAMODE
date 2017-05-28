package tieba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tieba.service.LoginServiceInterface;
import tieba.utils.SendMessage;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginServiceInterface loginService;
	
	@RequestMapping(value="/loginPage",method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="/isvalid",method=RequestMethod.POST)
	public ModelAndView isvalid(HttpServletRequest request, HttpServletResponse response){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println(username+"\n"+password);
		ModelAndView mv = new ModelAndView();
		
		String message = loginService.isValid(request, response);
		if(message.equals("登录成功")){
			SendMessage.add(request);
			mv.setViewName("redirect:/user/index");
		}else{
			mv.setViewName("redirect:/login/loginPage");
		}
		mv.addObject("message", message);
		return mv;
	}
	
	@RequestMapping(value="/registerPage")
	public ModelAndView registerPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		String message = loginService.register(request, response);
		mv.addObject("message",message);
		if(message == "注册成功"){
			SendMessage.add(request);
			mv.setViewName("redirect:/user/index");
		}else{
			mv.setViewName("redirect:/login/registerPage");
		}
		return mv;
	}
}
