package com.hack.hackathon.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class User  {

	@Id
	private String id;
	private String image;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	private String role;
	private String number;

}
