package com.project.questapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.User;
import com.project.questapp.exceptions.UserActivityNotFoundException;
import com.project.questapp.exceptions.UserNotFoundException;
import com.project.questapp.responses.UserResponse;
import com.project.questapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers().stream().map(u -> new UserResponse(u)).toList();
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User newUser) {
		User user = userService.saveOneUser(newUser);
		if (user != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{userId}")
	public UserResponse getOneUser(@PathVariable Long userId) {
		User user = userService.getOneUserById(userId);
		if (user == null) {// custom exception'ımız
			throw new UserNotFoundException();
		}
		return new UserResponse(user);
	}

	@GetMapping("name/{userName}") // isim ile aratmak için
	public UserResponse getOneUserByUserName(@PathVariable String userName) {
		User user = userService.getOneUserByUserName(userName);
		if (user == null) {// custom exception'ımız
			throw new UserNotFoundException();
		}
		return new UserResponse(user);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		User user = userService.updateOneUser(userId, newUser);
		if (user != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping("/{userId}")
	public String deleteOneUser(@PathVariable Long userId) {
		User user = userService.getOneUserById(userId);
		if (user == null) {// custom exception'ımız
			throw new UserNotFoundException();
		} else {
			userService.deleteById(userId);
			return "user deleted";// çıktı olarak postman vb'ye gönderdik
		}
	}

	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId) {
		if (userService.getUserActivity(userId) == null) {
			throw new UserActivityNotFoundException();
		} else {
			return userService.getUserActivity(userId);
		}
	}

	@ExceptionHandler(UserNotFoundException.class) // custom exception'ımız
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String handleUserNotFound() {
		return "user can't found"; // çıktı olarak postman vb'ye gönderdik
	}

	@ExceptionHandler(UserActivityNotFoundException.class) // custom exception'ımız
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String handleUserActiviyNotFound() {
		return "user activity can't found"; // çıktı olarak postman vb'ye gönderdik
	}
}
