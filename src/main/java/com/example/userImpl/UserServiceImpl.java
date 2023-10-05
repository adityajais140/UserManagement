package com.example.userImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RoleDto;
import com.example.domain.User;
import com.example.domain.UserDto;
import com.example.domain.UserRole;
import com.example.repo.RoleRepository;
import com.example.repo.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Object addUser(UserDto userdto) {
		List<UserRole> roleList=new ArrayList<>();
       User user=new User();
       UserRole role=roleRepository.findByName(userdto.getRole());
       roleList.add(role);
       user.setRoles(roleList);
       user.setName(userdto.getName());
       userRepository.save(user);
       return user;
	}


	@Override
	public Object updateUser(UserDto userdto) {
		 User userData=new User();
	       Optional<User> user=userRepository.findById(userdto.getId());
	       if(user.isPresent()) {
	    	    userData=user.get();
	     	   userData.setName(userdto.getName());
	    	//   userRepository.save(userData);
	       }
		return userData;
	}


	@Override
	public Object getUser() {
	      List<User> user=userRepository.findAll();
		return user;
	}

	@Override
	public Object deleteUser(Long id) {
		 User userData=new User();
		Optional<User> user=userRepository.findById(id);
		if(user.isPresent()) {
			userData=user.get();
			userData.setDeleted(true);
			userRepository.save(userData);
		}
		return userData;
	}

	@Override
	public Object addRole(RoleDto roleDto) {
		UserRole role=new UserRole();
		role.setRole(roleDto.getRole());
		role.setRankId(roleDto.getRank());
		return role;
	}


	
}
