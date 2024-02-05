package net.codejava;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bonjour")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() {
		return "Gaanesh PLAIN!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayJsonHello() {
		return "{\"name\":\"greeting\", \"message\":\"Gaanesh JSON!\"}";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHTMLHello() {
		return "<html><title>Hello</title><body><h1>Ganesh HTML</h1><body></html>";
	}
}
