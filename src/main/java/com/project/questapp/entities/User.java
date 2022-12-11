package com.project.questapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.project.questapp.validations.UniqueUserName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

    //@Column(nullable = false)
	@NotNull(message = "{questapp.constraints.username.NotNull.message}")
	@Size(min = 4, max = 24, message = "{questapp.constraints.username.Size.message}")
    @UniqueUserName
	String userName;

    @Column(nullable = false)
	String password;
    
	int avatar;
}
