package com.logan.loginAndRegistration.mvc.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.logan.loginAndRegistration.mvc.models.LoginUser;
import com.logan.loginAndRegistration.mvc.models.User;
import com.logan.loginAndRegistration.mvc.repositories.UserRepository;

@Service
public class UserService {

	// Initialize Repository

	@Autowired
	private UserRepository userRepository;

	// Find

	public User findById( Long id ) {

		Optional< User > user = userRepository.findById( id );

		if ( user.isPresent() )
			return user.get();

		return null;

	}

	// Register

	public User register(

		User user,
		BindingResult result

	) {

		if ( userRepository.findByEmail( user.getEmail() ).isPresent() )
			result.rejectValue( "email", "Unique", "Invalid email." );

		if ( ! user.getPassword().equals( user.getConfirm() ) )
			result.rejectValue( "confirm", "Matches", "Passwords do not match." );

		if ( result.hasErrors() )
			return null;

		String hashedPassword = BCrypt.hashpw( user.getPassword(), BCrypt.gensalt() );
		user.setPassword( hashedPassword );

		return userRepository.save( user ); // create new user

	}

	// Login

	public User login(

		LoginUser login,
		BindingResult result

	) {

		if ( result.hasErrors() )
			return null;

		Optional< User > optional = userRepository.findByEmail( login.getEmail() );

		if ( ! optional.isPresent() ) {

			result.rejectValue( "email", "Unique", "Invalid email." );

			return null;

		}

		User user = optional.get();

		if ( ! BCrypt.checkpw( login.getPassword(), user.getPassword() ) )
			result.rejectValue( "password", "Matches", "Invalied password." );

		if ( result.hasErrors() )
			return null;

		return user;

	}

}
