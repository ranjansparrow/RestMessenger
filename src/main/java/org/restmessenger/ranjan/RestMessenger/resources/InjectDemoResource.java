package org.restmessenger.ranjan.RestMessenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamusingAnnotations(@MatrixParam("authId") String auth,
			@HeaderParam("password") String password,
			@CookieParam("Idea-1b961404") String cookie){
		return "Matrix param is " + auth + "Header param is " + password 
				+ "cookie is " + cookie;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriinfo , @Context HttpHeaders httpheaders){
		String cookies = httpheaders.getCookies().toString();
		String path = uriinfo.getAbsolutePath().toString();
		return path + cookies;
	}
}
