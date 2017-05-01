package org.restmessenger.ranjan.RestMessenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.restmessenger.ranjan.RestMessenger.model.ErrorMessage;


public class GenericExceptionHandler implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
				500, "http://www.ranjansapkota.com.np");
		return Response.status(Status.INTERNAL_SERVER_ERROR).
				entity(errorMessage).
				build();
	}

}
