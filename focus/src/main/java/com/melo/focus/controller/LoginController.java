package com.melo.focus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.redisson.api.RMapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melo.focus.domain.basic.User;
import com.melo.focus.domain.vm.ResourceVM;
import com.melo.focus.service.ResourceService;
import com.melo.focus.util.Constants;
import com.melo.focus.util.Message;


@Controller
public class LoginController {
	
	@Autowired
	ResourceService resourceService;
	
	@RequestMapping(value="/focus/userlogin")
	@ResponseBody
	public Message<String> userLogin(User user){
		if(user==null){
			//return "login";
			return Message.ok("登录失败");
		}
		
		/*if(SecurityUtils.getSubject().getSession().getAttribute("login")!=null){
			return "index";
		}*/
		//两个session是一样的。 
		/*if(session.getAttribute("login")!=null){
			return Message.ok(Constants.SUCCESS);
		}*/
		
		String account=user.getLoginName();
		String password=user.getPassword();
		UsernamePasswordToken token = new UsernamePasswordToken(account,password,false);
		token.setRememberMe(true); 
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			//此步将 调用realm的认证方法
		} catch(IncorrectCredentialsException e){
			//这最好把 所有的 异常类型都背会
			//model.addAttribute("message", "密码错误");
			return Message.ok("密码错误");
		} catch (AuthenticationException e) {
			//model.addAttribute("message", "登录失败");
			return Message.ok("登录失败");
		}
		
		return Message.ok(Constants.SUCCESS);
	}
	
	//配合shiro配置中的默认访问url
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String getLogin(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response){

		return "login";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(){
		System.out.println("访问了后端 /  请求");
		return "login";
	}
	
	/**
	* 退出
	 * @return
	 */
	@RequestMapping(value="/focus/logout",method =RequestMethod.GET)
	public String logout(HttpServletRequest request){
		
		//subject的实现类DelegatingSubject的logout方法，将本subject对象的session清空了
		//即使session托管给了redis ，redis有很多个浏览器的session
		//只要调用退出方法，此subject的、此浏览器的session就没了
		try {
			User user = (User)SecurityUtils.getSubject().getPrincipal();
			String userId=user.getId();
			resourceService.deleteRedis(userId);
			//退出
			SecurityUtils.getSubject().logout();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "login";
	}
	
	@RequestMapping(value="403",method=RequestMethod.GET)
	public String unAuth(){
		
		return "403";
	}

	
	/*@RequestMapping(value="play")
	public String play(){
		System.out.println("11111");
		return "NewFile";
	}*/
}
