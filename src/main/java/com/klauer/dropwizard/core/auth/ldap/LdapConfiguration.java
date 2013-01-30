package com.klauer.dropwizard.core.auth.ldap;

public class LdapConfiguration {

    private String server;
    private int port;
    private String userDn;
    private String password;

    private String searchDn;
    private String rolesDn;

    public String getServer() {
	return server;
    }

    public void setServer(String server) {
	this.server = server;
    }

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }

    public String getUserDn() {
	return userDn;
    }

    public void setUserDn(String userDn) {
	this.userDn = userDn;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getSearchDn() {
	return searchDn;
    }

    public void setSearchDn(String searchDn) {
	this.searchDn = searchDn;
    }

    public String getRolesDn() {
	return rolesDn;
    }

    public void setRolesDn(String rolesDn) {
	this.rolesDn = rolesDn;
    }

}
