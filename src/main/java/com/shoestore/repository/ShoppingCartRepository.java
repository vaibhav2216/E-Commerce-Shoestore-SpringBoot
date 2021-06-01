package com.shoestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoestore.domain.ShoppingCart;

@Repository  
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

}
