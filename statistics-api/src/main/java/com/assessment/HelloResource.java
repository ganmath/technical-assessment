package com.assessment;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() {
		return "PLAIN Hello Gaanesh!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayJsonHello() {
		return "{\"name\":\"greeting\", \"message\":\"JSON Hello Gaanesh!\"}";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() {
		return "<html><title>Hello</title><body><h1>HTML Hello Gaanesh11</h1><body></html>";
	}
}
