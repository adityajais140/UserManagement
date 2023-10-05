package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long>{

	UserRole findByName(String name);

}
