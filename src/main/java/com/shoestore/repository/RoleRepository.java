package com.shoestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoestore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByname(String name);
	

}
