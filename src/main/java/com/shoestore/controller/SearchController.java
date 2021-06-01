package com.shoestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoestore.domain.Shoe;
import com.shoestore.domain.User;
import com.shoestore.service.Shoeservice;
import com.shoestore.service.UserService;

@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Shoeservice shoeService;
	
	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model,Principal principal
			) {
		
		if(principal !=null) {
			String username =principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory ="active"+category;
		model.addAttribute(classActiveCategory, true);
		
		List<Shoe> shoeList = shoeService.findByCategory(category);
		
		if(shoeList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shoeshelf";
		}
		model.addAttribute("shoeList", shoeList);
		return "shoeshelf";
		
	}
	
	
	@RequestMapping("/searchShoe")
	public String searchShoe(
			@RequestParam("keyword") String keyword,
			Principal principal,Model model
			) {
		if(principal !=null) {
			String username =principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		List<Shoe> shoeList = shoeService.blurrySearch(keyword);
		if(shoeList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "shoeshelf";
		}
		model.addAttribute("shoeList", shoeList);
		return "shoeshelf";
	}
	
	
	
}
