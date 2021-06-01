package com.shoestore.service;

import java.util.List;

import com.shoestore.domain.Shoe;

public interface Shoeservice {
	List<Shoe> findAll ();
	
	Shoe findOne(Long id);
	
	List<Shoe> findByCategory(String category);
	
	List<Shoe> blurrySearch(String title);

}
	
