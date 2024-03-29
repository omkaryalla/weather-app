package com.wether.app.authservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wether.app.authservice.domain.Favourite;


@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer>{
	
	public List<Favourite> findByUserName(String userName);
	public void deleteByUserName(String userName);

}