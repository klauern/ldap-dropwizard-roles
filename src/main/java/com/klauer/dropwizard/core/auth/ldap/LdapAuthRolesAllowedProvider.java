package com.klauer.dropwizard.core.auth.ldap;

import com.klauer.dropwizard.core.auth.AuthRolesAllowed;
import com.klauer.dropwizard.core.auth.BasicCredentialsWithRoles;
import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.yammer.dropwizard.auth.Authenticator;

public class LdapAuthRolesAllowedProvider<T> implements
        InjectableProvider<AuthRolesAllowed, Parameter> {

    private final Authenticator<BasicCredentialsWithRoles, T> authenticator;
    private final String realm;

    public LdapAuthRolesAllowedProvider(
            Authenticator<BasicCredentialsWithRoles, T> authenticator,
            String realm) {
        this.authenticator = authenticator;
        this.realm = realm;
    }

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable<?> getInjectable(ComponentContext ic, AuthRolesAllowed a,
            Parameter c) {
        return new LdapAuthRolesAllowedInjectable<T>(authenticator, a, realm);
    }

}
