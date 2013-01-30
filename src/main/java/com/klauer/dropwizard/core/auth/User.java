package com.klauer.dropwizard.core.auth;

import java.util.Set;

public class User {

    private final String name;
    private final Set<String> roles;

    public User(String name, Set<String> roles) {
	this.name = name;
	this.roles = roles;
    }

    public String getName() {
	return this.name;
    }

    public Set<String> getRoles() {
	return this.roles;
    }

}
