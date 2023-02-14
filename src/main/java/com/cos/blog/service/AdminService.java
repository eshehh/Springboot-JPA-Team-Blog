package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
	
	public Page<User> 회원목록(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	

}
