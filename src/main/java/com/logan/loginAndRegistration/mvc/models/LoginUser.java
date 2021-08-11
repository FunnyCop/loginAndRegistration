package com.logan.loginAndRegistration.mvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {

	// Fields

	@NotEmpty( message = "Email is required." )
	@Email( message = "Please enter a valid email." )
	private String email; // loginUser.email

	@NotEmpty( message = "Password is required." )
	@Size( min = 8, max = 255, message = "Password must be between 8 and 128 characters long." )
	private String password; // loginUser.password

	// Getters

	public String getEmail()
		{ return this.email; } // loginUser.email

	public String getPassword()
		{ return this.password; } // loginUser.password

	// Setters

	public void setEmail( String email )
		{ this.email = email; } // loginUser.email

	public void setPassword( String password )
		{ this.password = password; } // loginUser.password

	// Constructors

	public LoginUser() {}

	public LoginUser(

		String email,
		String password

	) {

		this.email = email;
		this.password = password;

	}

}
