package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.RoleDto;
import com.example.domain.UserDto;
import com.example.service.UserService;

@RestController
public class UserController {

	public static final String USER = "api/v1/user";
	public static final String ROLE = "api/v1/role";
	public static final String URL="api/v1/";
	@Autowired
	UserService userService;

	@PostMapping(USER)
    public Object  addUser(@RequestBody UserDto userdto) {
      return userService.addUser(userdto);
    }
	
	@PutMapping(USER)
    public Object  updateUser(@RequestBody UserDto userdto) {
      return userService.updateUser(userdto);
    }
	
	@GetMapping(USER)
    public Object  getUser() {
      return userService.getUser();
    }
	
	@DeleteMapping(USER)
    public Object  deleteUser(@RequestParam Long id) {
      return userService.deleteUser(id);
    }
	
	@PostMapping(ROLE)
    public Object  addRole(@RequestParam String  role,@RequestParam Long rank) {
      return userService.addRole(role,rank);
    }
	
	@PostMapping(URL+"sendMail")
    public Object  sendMail() {
      return userService.sendMail();
    }
	
	@GetMapping(URL+"employee")
	public Object getTopFiveSalriedEmployee() {
	      return userService.getTopFiveSalriedEmployee();

	}
	
	

}
