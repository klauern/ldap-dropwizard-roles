package com.klauer.dropwizard.core.auth.ldap;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.google.common.base.Optional;
import com.klauer.dropwizard.core.auth.AuthRolesAllowed;
import com.klauer.dropwizard.core.auth.BasicAuth;
import com.klauer.dropwizard.core.auth.BasicCredentialsWithRoles;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

@Provider
public class LdapAuthRolesAllowedInjectable<T> extends
        AbstractHttpContextInjectable<T> {
    private static final String PREFIX = "Basic";
    private static final String HEADER_NAME = "WWW-Authenticate";
    private static final String HEADER_VALUE = PREFIX + " realm=\"%s\"";
    private final Authenticator<BasicCredentialsWithRoles, T> authenticator;
    private final String realm;
    private final AuthRolesAllowed allowed;

    public LdapAuthRolesAllowedInjectable(
            Authenticator<BasicCredentialsWithRoles, T> authenticator,
            AuthRolesAllowed allowed, String realm) {
        this.authenticator = authenticator;
        this.allowed = allowed;
        this.realm = realm;
    }

    public Authenticator<BasicCredentialsWithRoles, T> getAuthenticator() {
        return authenticator;
    }

    @Override
    public T getValue(HttpContext c) {
        BasicCredentials bc = BasicAuth.getCredentials(c);

        if (bc != null && bc.getUsername() != null && bc.getPassword() != null) {
            BasicCredentialsWithRoles credentials = new BasicCredentialsWithRoles(
                    bc.getUsername(), bc.getPassword(), allowed.value());

            Optional<T> result;
            try {
                result = authenticator.authenticate(credentials);
            } catch (AuthenticationException e) {
                throw new WebApplicationException(
                        Response.Status.INTERNAL_SERVER_ERROR);
            }
            if (result.isPresent()) {
                return result.get();
            }
        }

        throw new WebApplicationException(Response
                .status(Response.Status.UNAUTHORIZED)
                .header(HEADER_NAME, String.format(HEADER_VALUE, realm))
                .entity("Credentials are required to access this resource.")
                .type(MediaType.TEXT_PLAIN_TYPE).build());
    }

}
