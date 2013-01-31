package com.klauer.dropwizard.core.auth;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

/**
 * A set of user-provided Basic Authentication credentials, consisting of a
 * username and a password.
 */
public class BasicCredentialsWithRoles {
    private final String username;
    private final String password;
    private final String[] roles_allowed;

    /**
     * Creates a new BasicCredentials with the given username and password.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     */
    public BasicCredentialsWithRoles(String username, String password,
            String[] roles_allowed) {
        this.username = checkNotNull(username);
        this.password = checkNotNull(password);
        this.roles_allowed = checkNotNull(roles_allowed);
    }

    /**
     * Returns the credentials' username.
     * 
     * @return the credentials' username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the credentials' password.
     * 
     * @return the credentials' password
     */
    public String getPassword() {
        return password;
    }

    public String[] getRolesAllowed() {
        return roles_allowed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result + Arrays.hashCode(roles_allowed);
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasicCredentialsWithRoles other = (BasicCredentialsWithRoles) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (!Arrays.equals(roles_allowed, other.roles_allowed))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BasicCredentialsWithRoles [username=" + username
                + ", password=" + password + ", roles_allowed="
                + Arrays.toString(roles_allowed) + "]";
    }

}
