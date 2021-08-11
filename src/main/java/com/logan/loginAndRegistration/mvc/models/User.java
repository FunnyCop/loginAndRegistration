package com.logan.loginAndRegistration.mvc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table( name = "users" )
public class User {

	// Columns/Fields

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id; // user.id

	@NotEmpty( message = "Email is required." )
	@Email( message = "Pealse enter a valid email." )
	private String email; // user.email

	@NotEmpty( message = "Password is required." )
	@Size( min = 8, max = 255, message = "Password must be between 8 and 255 characters long." )
	private String password; // user.password

	@Transient
	@NotEmpty( message = "Confirm Password is required." )
	@Size( min = 8, max = 255, message = "Confirm Password must be between 8 and 255 characters long." )
	private String confirm; // user.confirm

	@Column( updatable = false )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date createdAt; // user.createdAt

	@DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date updatedAt; // user.updatedAt

	// created_at generator

    @PrePersist
 	protected void onCreate()
    	{ this.createdAt = new Date(); } // user.createdAt

    // updated_at generator

 	@PreUpdate
 	protected void onUpdate()
 		{ this.updatedAt = new Date(); } // user.updatedAt

	// Getters

	public Long getID()
		{ return this.id; } // user.id

	public String getEmail()
		{ return this.email; } // user.email

	public String getPassword()
		{ return this.password; } // user.password

	public String getConfirm()
		{ return this.confirm; } // user.confirm

	// Setters

	public void setID( Long id )
		{ this.id = id; } // user.id

	public void setEmail( String email )
		{ this.email = email; } // user.email

	public void setPassword( String password )
		{ this.password = password; } // user.password

	public void setConfirm( String confirm )
		{ this.confirm = confirm; } // user.confirm

	// Constructors

	public User() {}

	public User(

		String email,
		String password,
		String confirm

	) {

		this.email = email;
		this.password = password;
		this.confirm = confirm;

	}

}
