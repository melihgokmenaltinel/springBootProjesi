package com.project.questapp.exceptions;

public class UserActivityNotFoundException extends RuntimeException {

	public UserActivityNotFoundException() {
		super();
	}

	public UserActivityNotFoundException(String message) {
		super(message);
	}
}
