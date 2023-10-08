package com.example.domain;

import lombok.Data;

@Data
public class UserDto {
	
	long id;
	
	String name;
	
	String role;
	
	long  rank;
	
	long salary;
	
	long supervisorId;
	

}
