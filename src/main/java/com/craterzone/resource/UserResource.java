package com.craterzone.resource;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import com.craterzone.dao.Dao;
import com.craterzone.exception.InternalServerErrorException;
import com.craterzone.exception.UserNotFoundException;
import com.craterzone.model.User;

@Path("/users")
public class UserResource {
	private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName()); 
	ObjectMapper mapper = new ObjectMapper();
	Dao dao = Dao.getInstance();

	@GET
	@Path("/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("user_id") final int uid) throws UserNotFoundException, InternalServerErrorException {
		User user = dao.get(uid);
		try {
			String jsonInString = mapper.writeValueAsString(user);
			LOGGER.log(Level.INFO, "user: "+jsonInString);
			return Response.status(Status.OK).entity(jsonInString).build();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "user not found id = "+uid);
			return Response.status(Status.NO_CONTENT).build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String jsonInString) {
		try {
			User user = mapper.readValue(jsonInString, User.class);
			Boolean bool = dao.update(user);
			if (bool == true) {
				return Response.status(Status.OK).entity("{\"msg\":\"updated succefully\"}").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String jsonInString) {
		try {
			User user = mapper.readValue(jsonInString, User.class);
			Boolean bool = dao.create(user);
			if (bool == true) {
				return Response.status(Status.OK).entity("{\"msg\":\"created successfully\"}").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(Status.NO_CONTENT).build();
	}

}
