package com.klauer.dropwizard.core.auth;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.B64Code;
import org.eclipse.jetty.util.StringUtil;

import com.sun.jersey.api.core.HttpContext;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

public class BasicAuth {

    private static final String PREFIX = "Basic";
    private static Logger LOGGER = Logger.getLogger(BasicAuth.class);

    public static BasicCredentials getCredentials(HttpContext c) {
	final String header = c.getRequest().getHeaderValue(
		HttpHeaders.AUTHORIZATION);
	try {
	    if (header != null) {
		final int space = header.indexOf(' ');
		if (space > 0) {
		    final String method = header.substring(0, space);
		    if (PREFIX.equalsIgnoreCase(method)) {
			final String decoded = B64Code.decode(
				header.substring(space + 1),
				StringUtil.__ISO_8859_1);
			final int i = decoded.indexOf(':');
			if (i > 0) {
			    final String username = decoded.substring(0, i);
			    final String password = decoded.substring(i + 1);
			    return new BasicCredentials(username, password);
			}
		    }
		}
	    }
	} catch (UnsupportedEncodingException e) {
	    LOGGER.debug("Error decoding credentials", e);
	} catch (IllegalArgumentException e) {
	    LOGGER.debug("Error decoding credentials", e);
	}
	return null;
    }
}
