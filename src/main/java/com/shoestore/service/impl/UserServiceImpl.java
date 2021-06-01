package com.shoestore.service.impl;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoestore.domain.ShoppingCart;
import com.shoestore.domain.User;
import com.shoestore.domain.security.PasswordResetToken;
import com.shoestore.domain.security.UserRole;
import com.shoestore.repository.PasswordResetTokenRepository;
import com.shoestore.repository.RoleRepository;
import com.shoestore.repository.ShoppingCartRepository;
import com.shoestore.repository.UserRepository;
import com.shoestore.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}
	
	@Override
	public void createPasswordResetTokenForUser(final User user,final String token) {
		final PasswordResetToken myToken =new PasswordResetToken(token ,user);
		passwordResetTokenRepository.save(myToken);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
//	@Transactional
	public User createUser(User user,Set<UserRole> userRoles) {
		
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info ("user {} already exists.Nothing will be done." , user.getUsername());
		}
		else
		{
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			
			ShoppingCart shoppingCart =new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
			shoppingCartRepository.save(shoppingCart);
			
			localUser =userRepository.save(user);

		}
		return localUser;
	}
	
	@Override
	public User findOne(Long Id){
	    Optional <User> optUser = userRepository.findById(Id); 
	    if (optUser.isPresent()) {
	        return optUser.get();
	    } else {
	        return null;
	    }
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
