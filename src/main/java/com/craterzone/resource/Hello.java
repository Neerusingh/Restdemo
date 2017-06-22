package com.craterzone.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {
	
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	public String saySimpleHello(){
		return "Hello .. its a plain text";
	}
	
	@Path("/abc")
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	public String saySimpleHelloMethod(){
		return "Hello .. its a plain textHGKGKGK";
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String saySimpleGetHello(){
		return "Hello .. its a post ";
	}
	
	@GET
    @Produces(MediaType.TEXT_XML)
	public String sayXmlHello(){
		 return "<?xml version=\"1.0\"?>" + "<hello> Hello...its a XMl text" + "</hello>";	
	}
	
	 @GET
	 @Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(){
		 return "<html> " + "<title>" + "Hello  </title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";	
	}
	 @GET
		@Path("/{param}")
		public Response getMsg(@PathParam("param") String msg) {

			String output = "Jersey say : " + msg;

			return Response.status(200).entity(output).build();

		}

}

