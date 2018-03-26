package org.luksze;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

class MyResourceConfig extends ResourceConfig {
    MyResourceConfig() {
        register(MyResource.class);
        register(new NotAcceptableExceptionExceptionMapper());
    }

    static class NotAcceptableExceptionExceptionMapper implements ExceptionMapper<NotAcceptableException> {
        @Override
        public Response toResponse(NotAcceptableException exception) {
            return Response.fromResponse(exception.getResponse()).entity("My custom text").build();
        }
    }
}