package com.project.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

 //eğer aradığımız userName varsa true, yoksa false döner
	boolean existsUserByUserName(String userName);
}
