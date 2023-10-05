package com.example.service;

import com.example.domain.RoleDto;
import com.example.domain.UserDto;

public interface UserService {

	Object addUser(UserDto userdto);

	Object updateUser(UserDto userdto);

	Object getUser();

	Object addRole(RoleDto roleDto);

	Object deleteUser(Long id);
	

}
