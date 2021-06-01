package com.shoestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoestore.domain.CartItem;
import com.shoestore.domain.Shoe;
import com.shoestore.domain.ShoppingCart;
import com.shoestore.domain.User;
import com.shoestore.service.CartItemService;
import com.shoestore.service.Shoeservice;
import com.shoestore.service.ShoppingCartService;
import com.shoestore.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private Shoeservice shoeService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}
	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("shoe") Shoe shoe,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		shoe = shoeService.findOne(shoe.getId());
		
		if (Integer.parseInt(qty) > shoe.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/shoeDetail?id="+shoe.getId();
		}
		
		CartItem cartItem = cartItemService.addShoeToCartItem(shoe, user, Integer.parseInt(qty));
		model.addAttribute("addShoeSuccess", true);
		
		return "forward:/shoeDetail?id="+shoe.getId();
	}
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(
			@ModelAttribute("id") Long cartItemId,
			@ModelAttribute("qty") int qty
			) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
	
}