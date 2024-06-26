package com.poscodx.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.security.AuthUser;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		
		return "user/join";
	}

	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			//			List<ObjectError> list = result.getAllErrors();
			//			for (ObjectError error : list) {
			//				System.out.println(error);
			//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);

		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	// 	@RequestMapping(value = "/login", method = RequestMethod.POST)
	// 	public String login(HttpSession session, UserVo vo, Model model) {
	// 		UserVo authUser = userService.getUser(vo.getEmail(), vo.getPassword());
	// 		if (authUser == null) {
	// 			model.addAttribute("email", vo.getEmail());
	// 			model.addAttribute("result", "fail");
	// 			return "user/login";
	// 		}
	// 		session.setAttribute("authUser", authUser);
	// 		return "redirect:/";
	// 	}
	//	
	// 	@RequestMapping("/logout")
	// 	public String logout(HttpSession session) {
	// 		session.removeAttribute("authUser");
	// 		session.invalidate();
	// 		
	// 		return "redirect:/";
	// 	}
	@Auth(role="ADMIN")
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		//@AuthUser UserVo authUser
		//UserVo authUser = (UserVo)session.getAttribute("authUser");

		// 		if (authUser == null) {
		// 			return "redirect:/"; 
		// 		}

		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);

		return "user/update";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, UserVo vo) {
		// access control
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/"; 
		}
		/////////////////////////
		vo.setNo(authUser.getNo());
		userService.update(vo);

		authUser.setName(vo.getName());
		return "redirect:/";
	}
}
