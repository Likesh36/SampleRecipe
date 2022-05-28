package com.recipe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipe.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u from User u WHERE u.username=:username ")
	public Optional<User> findByName(@Param("username") String userName);

}
