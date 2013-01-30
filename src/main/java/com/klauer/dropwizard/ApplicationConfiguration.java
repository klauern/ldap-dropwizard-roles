package com.klauer.dropwizard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.klauer.dropwizard.core.auth.ldap.LdapConfiguration;
import com.yammer.dropwizard.config.Configuration;

/**
 * @author Nick Klauer (klauer@gmail.com)
 * 
 */
public class ApplicationConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private LdapConfiguration ldap = new LdapConfiguration();

    public LdapConfiguration getLdapConfiguration() {
	return ldap;
    }

}
