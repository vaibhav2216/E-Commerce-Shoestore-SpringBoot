package com.shoestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoestore.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
	
	User findByEmail(String email);

}
