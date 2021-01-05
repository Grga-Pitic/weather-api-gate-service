package com.pet.main.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api")
public class Api {

	@Id
	private int id;
	
	@Column(unique=true)
	private String name;
	
	private String token;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getToken() {
		return token;
	}
	
}
