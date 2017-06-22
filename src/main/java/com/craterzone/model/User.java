package com.craterzone.model;

public class User {
private final Integer id;
private final String name;
private final Integer password;
private final String email;

@SuppressWarnings("unused")
private User(){
	this(null, null, null, null);
}

public User(final Integer id, final String name, final Integer password, final String email) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.email = email;
}

public static User createWithId(final Integer id){
	return new User(id, null, null, null);
}

public static User createWithEmail(final String email, final User u){
	return new User(u.getId(), u.getName(), u.getPassword(), email);
}


public Integer getId() {
	return id;
}

public String getName() {
	return name;
}

public Integer getPassword() {
	return password;
}

public String getEmail() {
	return email;
}
}
