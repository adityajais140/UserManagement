package com.example.repo;

import org.springframework.stereotype.Repository;

import com.example.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
