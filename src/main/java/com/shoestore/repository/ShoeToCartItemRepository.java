package com.shoestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.shoestore.domain.CartItem;
import com.shoestore.domain.ShoeToCartItem;

@Transactional
public interface ShoeToCartItemRepository extends CrudRepository<ShoeToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
