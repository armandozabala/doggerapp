package com.doggers.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doggers.demo.entity.Role;


@Repository
public interface RolRepository extends JpaRepository<Role, Long> {

	
	public Role findByName(String name);
	
}
