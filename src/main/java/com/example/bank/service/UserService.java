package com.example.bank.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.bank.entity.User;

public interface UserService {

	List<User> getAllUser();

	User getUserById(int userId);

	User registerUser(User user);

	User updateUserDetails(User user, int userId);

	ResponseEntity<User> deleteUser(int userId);


}
