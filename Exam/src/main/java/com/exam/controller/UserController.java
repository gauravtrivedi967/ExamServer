package com.exam.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping(path ="/user")//to access this code
@CrossOrigin("*")
public class UserController {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//creating user
	@PostMapping(path = "/")//REquestbody to get data as it is in json
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		//encoding password with bcryptpasswordencoder
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles =new HashSet<>();
		
		Role role =new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole= new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
	}
	@GetMapping(path = "/{username}")
	public User getUser(@PathVariable("username") String username) 
		{
			return this.userService.getUser(username);
		}
	//delete user by id
	@DeleteMapping(path = "/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) 
	{
		this.userService.deleteUser(userId);
	}
	//update api
	
}