package com.example.gja.comm;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

@Path("http://v1-dot-gja-rest.appspot.com/api")
public interface Comm extends RestService {
	
	static String server = "http://v1-dot-gja-rest.appspot.com/api";
    
	//User login
	@GET
    @Path("/users?user=<{username}>&pwd=<{password}>")
    public void userLogin(@PathParam("username") String username, @PathParam("password") String password, MethodCallback<String> callback);
	
	//User register
	@GET
    @Path("/users?user=<{username}>&pwd=<{password}>")
    public void userRegister(@PathParam("username") String username, @PathParam("password") String password, MethodCallback<String> callback);
	
	
	
}

