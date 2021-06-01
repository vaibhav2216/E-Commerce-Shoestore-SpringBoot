package com.shoestore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoestore.domain.Shoe;
import com.shoestore.repository.ShoeRepository;
import com.shoestore.service.Shoeservice;

@Service
public class ShoeServiceImpl implements Shoeservice {
	
	@Autowired
	private ShoeRepository shoeRepository;
	
	public List<Shoe> findAll(){
		List<Shoe> shoeList = (List<Shoe>) shoeRepository.findAll();
		
		return shoeList;
	}

	
	public Shoe findOne(Long Id){
	    Optional <Shoe> optUser = shoeRepository.findById(Id); 
	    if (optUser.isPresent()) {
	        return optUser.get();
	    } else {
	        return null;
	    }
	}
	
	public List<Shoe> findByCategory(String category){
		
		List<Shoe> shoeList=shoeRepository.findByCategory(category);
		
		return shoeList;
	}
	
	public List<Shoe> blurrySearch(String title){
		List<Shoe> shoeList=shoeRepository.findByTitleContaining(title);
		return shoeList;
	}

}
