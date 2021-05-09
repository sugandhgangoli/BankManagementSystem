
package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.User;
import com.example.bank.exception.APIException;
import com.example.bank.service.UserService;

@RestController

@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// Get All Users

	@GetMapping
	public List<User> getAllUser() {
		return this.userService.getAllUser();
	}

	// Get Users By Id

	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") int userId) {
		return this.userService.getUserById(userId);
	}

	// Create a Users

	@PostMapping
	public User registerUser(@RequestBody User user) throws APIException {
		return this.userService.registerUser(user);
	}

	// Update the Users by Id

	@PutMapping("/{id}")
	public User updateUserDetails(@RequestBody User user, @PathVariable(value = "id") int userId) {
		return this.userService.updateUserDetails(user, userId);
	}

	// Delete the Users by Id

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") int userId) {
		return this.userService.deleteUser(userId);
	}
}
