package org.openhmis.webservice;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import org.apache.log4j.Logger;
import org.openhmis.code.ClientNameDataQuality;
import org.openhmis.dto.ClientDTO;
import org.openhmis.manager.ClientManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;

import org.openhmis.util.Authentication;

@Path("/authenticate")
public class AuthenticationService {

	public AuthenticationService() {}

	@POST
	@Path("/google")
	public String authenticate(@Context SecurityContext sc, String code) {
		return Authentication.getGoogleToken(code);
	}

}
