package com.shoestore.service.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoestore.domain.CartItem;
import com.shoestore.domain.Shoe;
import com.shoestore.domain.ShoeToCartItem;
import com.shoestore.domain.ShoppingCart;
import com.shoestore.domain.User;
import com.shoestore.repository.CartItemRepository;
import com.shoestore.repository.ShoeToCartItemRepository;
import com.shoestore.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ShoeToCartItemRepository shoeToCartItemRepository;
	
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getShoe().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2,RoundingMode.HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}
	
	public CartItem addShoeToCartItem(Shoe shoe, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if(shoe.getId() == cartItem.getShoe().getId()) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(new BigDecimal(shoe.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setShoe(shoe);
		
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(shoe.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		ShoeToCartItem shoeToCartItem = new ShoeToCartItem();
		shoeToCartItem.setShoe(shoe);
		shoeToCartItem.setCartItem(cartItem);
		shoeToCartItemRepository.save(shoeToCartItem);
		
		return cartItem;
	}
	public CartItem findById(Long id) {
		
		Optional <CartItem> optUser = cartItemRepository.findById(id); 
	    if (optUser.isPresent()) {
	        return optUser.get();
	    } else {
	        return null;
	    }
	}
	
	public void removeCartItem(CartItem cartItem) {
		shoeToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}
	
}
