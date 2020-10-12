package com.doggers.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doggers.demo.entity.Role;
import com.doggers.demo.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return rolRepository.findByName(name);
	}

}
