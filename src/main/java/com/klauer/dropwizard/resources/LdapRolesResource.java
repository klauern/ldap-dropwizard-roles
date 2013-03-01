package com.klauer.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.klauer.dropwizard.core.auth.AuthRolesAllowed;
import com.klauer.dropwizard.core.auth.User;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class LdapRolesResource {

    final static Logger LOG = LoggerFactory.getLogger(LdapRolesResource.class);

    public LdapRolesResource() {
    }

    @GET
    @Path("/not-allowed-in")
    public String validateAgainstRoles(@AuthRolesAllowed({ "LDAP_ROLE",
            "ANOTHER_ROLE" }) User user) {
        return "You must have gotten through with one of the two roles this app required here.  Congratulations";
    }

    @GET
    @Path("/authenticate")
    public String authenticate(@AuthRolesAllowed User user) {
        StringBuilder message = new StringBuilder("You've Authenticated!\n");
        message.append("Your Roles include:\n");
        for (String role : user.getRoles()) {
            message.append(role);
            message.append("\n");
        }
        return message.toString();
    }

    @GET
    @Path("/noauth")
    public String noAuthentication() {
        return "No authentication, but you made it anyway!";
    }
}
