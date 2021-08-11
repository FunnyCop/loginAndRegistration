package com.logan.loginAndRegistration.mvc.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logan.loginAndRegistration.mvc.models.LoginUser;
import com.logan.loginAndRegistration.mvc.models.User;
import com.logan.loginAndRegistration.mvc.services.UserService;

@Controller
public class UserController {

	// Initialize Service

	private final UserService userService;

	public UserController( UserService userService )
		{ this.userService = userService; }

	// Index

	@RequestMapping( "/" )
	public String index( HttpSession session ) {

		if ( session.getAttribute( "id" ) == null )
			return "redirect:/registration";

		return "redirect:/home";

	}

	// Home

	@RequestMapping( "/home" )
	public String home(

		HttpSession session,
		Model model

	) {

		if ( session.getAttribute( "id" ) == null )
			return "redirect:/registration";

		User user = userService.findById(  ( Long ) session.getAttribute( "id" ) );

		if ( user.equals( null ) ) {

			session.setAttribute( "id", null );

			return "redirect:/registration";

		}

		model.addAttribute( "user", user );

		return "home.jsp";

	}

	// Registration

	@RequestMapping( "/registration" )
	public String registration( Model model ) {

		model.addAttribute( "user", new User() );
		return "registration.jsp";

	}

	@RequestMapping( value = "/registration", method = RequestMethod.POST )
	public String submitRegistration(

		@Valid @ModelAttribute( "user" ) User user,
		BindingResult result,
		HttpSession session

	) {

		if ( result.hasErrors() )
			return "registration.jsp";

		User newUser = userService.register( user, result );

		if ( result.hasErrors() )
			return "registration.jsp";

		session.setAttribute( "id", newUser.getID() );

		return "redirect:/home";

	}

	// Login

	@RequestMapping( "/login" )
	public String login( @ModelAttribute( "login" ) LoginUser login )
		{ return "login.jsp"; }

	@RequestMapping( value = "/login", method = RequestMethod.POST )
	public String submitLogin(

		@Valid @ModelAttribute( "login" ) LoginUser login,
		BindingResult result,
		Model model,
		HttpSession session

	) {

		if ( result.hasErrors() )
			return "login.jsp";

		User user = userService.login( login, result );

		if ( result.hasErrors() )
			return "login.jsp";

		session.setAttribute( "id", user.getID() );

		return "redirect:/home";

	}

	// Logout

	@RequestMapping( "/logout" )
	public String logout( HttpSession session ) {

		session.setAttribute( "id", null );

		return "redirect:/registration";

	}

}
