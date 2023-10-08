package com.example.service;

import com.example.domain.RoleDto;
import com.example.domain.UserDto;

public interface UserService {

	Object addUser(UserDto userdto);

	Object updateUser(UserDto userdto);

	Object getUser();

	Object deleteUser(Long id);

	Object addRole(String role, Long rank);

	Object sendMail();

	Object getTopFiveSalriedEmployee();
	

}
