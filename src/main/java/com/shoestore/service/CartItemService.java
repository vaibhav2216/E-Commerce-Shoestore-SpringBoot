package com.shoestore.service;

import java.util.List;

import com.shoestore.domain.CartItem;
import com.shoestore.domain.Shoe;
import com.shoestore.domain.ShoppingCart;
import com.shoestore.domain.User;

public interface CartItemService{
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addShoeToCartItem(Shoe shoe,User user,int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
}
