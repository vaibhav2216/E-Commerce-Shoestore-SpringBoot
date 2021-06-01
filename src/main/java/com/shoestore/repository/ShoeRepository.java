package com.shoestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shoestore.domain.Shoe;

public interface ShoeRepository extends CrudRepository<Shoe,Long>{

	List<Shoe> findByCategory(String category);

	List<Shoe> findByTitleContaining(String title);
}
