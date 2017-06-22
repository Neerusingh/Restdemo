package com.craterzone.exception.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.craterzone.exception.UserNotFoundException;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException>{

	@Override
	public Response toResponse(UserNotFoundException exception) {
		
		return Response.status(Status.NOT_FOUND).build();
	}

}
