package com.klauer.dropwizard;

import com.klauer.dropwizard.core.auth.User;
import com.klauer.dropwizard.core.auth.ldap.LdapAuthRolesAllowedProvider;
import com.klauer.dropwizard.core.auth.ldap.LdapAuthenticator;
import com.klauer.dropwizard.core.auth.ldap.LdapConfiguration;
import com.klauer.dropwizard.core.auth.ldap.LdapConnectionFactory;
import com.klauer.dropwizard.resources.LdapRolesResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ExampleService extends Service<ApplicationConfiguration> {

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
	bootstrap.setName("ldap-roles-authorization");
    }

    @Override
    public void run(ApplicationConfiguration configuration,
	    Environment environment) throws Exception {
	final LdapConfiguration ldap = configuration.getLdapConfiguration();

	environment.addResource(new LdapRolesResource());
	LdapConnectionFactory ldap_factory = new LdapConnectionFactory(
		ldap.getServer(), ldap.getPort(), ldap.getUserDn(),
		ldap.getPassword());
	final LdapAuthenticator ldap_authenticator = new LdapAuthenticator(
		ldap_factory, ldap.getSearchDn(), ldap.getRolesDn());
	environment.addProvider(new LdapAuthRolesAllowedProvider<User>(
		ldap_authenticator, "LDAP Role Authentication"));
    }

    public static void main(String[] args) throws Exception {
	new ExampleService().run(args);
    }

}
