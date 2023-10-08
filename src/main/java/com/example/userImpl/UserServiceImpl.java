package com.example.userImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.RoleDto;
import com.example.domain.User;
import com.example.domain.UserDto;
import com.example.domain.UserRole;
import com.example.repo.RoleRepository;
import com.example.repo.UserRepository;
import com.example.service.MailService;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	MailService mailService;
	
	@Override
	public Object addUser(UserDto userdto) {
		List<UserRole> roleList = new ArrayList<>();
		User user = new User();
		UserRole role = roleRepository.findByRole(userdto.getRole());
		User supervisor=userRepository.findById(userdto.getSupervisorId()).get();
		roleList.add(role);
		user.setRoles(roleList);
		user.setName(userdto.getName());
		user.setSalary(userdto.getSalary());
		user.setSupervisor(supervisor);
		userRepository.save(user);
		return user;
	}

	@Override
	public Object updateUser(UserDto userdto) {
		User userData = new User();
		Optional<User> user = userRepository.findById(userdto.getId());
		if (user.isPresent()) {
			userData = user.get();
			userData.setName(userdto.getName());
			userRepository.save(userData);
		}
		return userData;
	}

	@Override
	public Object getUser() {
		List<User> user = userRepository.findAll();
		List<Map<Object, Object>> userList = new ArrayList<>();
		for(User userData:user){
		Map<Object, Object> map = new HashMap<>();
		map.put("name", userData.getName());
		map.put("roles", userData.getRoles().stream().map(i->i.getRole()).collect(Collectors.toList()));
		map.put("salary", userData.getSalary());
		userList.add(map);
		}

		return userList;
	}

	@Override
	public Object deleteUser(Long id) {
		User userData = new User();
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userData = user.get();
			userData.setDeleted(true);
			userRepository.save(userData);
		}
		return userData;
	}

	@Override
	public Object addRole(String role, Long rank) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setRankId(rank);
		return roleRepository.save(userRole);
	}

	@Override
	public Object sendMail() {
		String to = "aditya.jaiswal@oodles.io";
		String subject = "Test Email";
		String body = "This is a test email.";
		mailService.sendEmail(to, subject, body);
		return true;
	}

	@Override
	public Object getTopFiveSalriedEmployee() {
		List<User> user = userRepository.findAll();
		
		// Sort the list by salary in descending order using lambda expression
		List<User> topFiveSalaryEmployee = user.stream()
		        .sorted(Comparator.comparingDouble(User::getSalary).reversed()) // Sort in descending order
		        .limit(5) // Limit to the first five elements
		        .collect(Collectors.toList());
		
		List<Map<Object, Object>> userDataList = new ArrayList<>();
		for(User userData:topFiveSalaryEmployee){
		Map<Object, Object> mapData = new HashMap<>();
		mapData.put("name", userData.getName());
		mapData.put("salary", userData.getSalary());
		userDataList.add(mapData);
		}
		return userDataList;
	}
	
}
