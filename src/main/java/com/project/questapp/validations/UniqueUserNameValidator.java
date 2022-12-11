package com.project.questapp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.project.questapp.repos.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@RequiredArgsConstructor
public final class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

	private final UserRepository userRepository;

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		return !userRepository.existsUserByUserName(userName);
	}

}