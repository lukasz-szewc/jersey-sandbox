package org.luksze;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Path("zoned")
public class ZoneTimeResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ZonedDateTime getIt() {
        return ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("Europe/Warsaw"));
    }

    static class ZonedDateTimeMessageBodyWriter implements MessageBodyWriter<ZonedDateTime> {
        @Override
        public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
            return aClass == ZonedDateTime.class;
        }

        @Override
        public void writeTo(ZonedDateTime zonedDateTime,
                            Class<?> aClass, Type type,
                            Annotation[] annotations,
                            MediaType mediaType,
                            MultivaluedMap<String, Object> multivaluedMap,
                            OutputStream outputStream) throws IOException, WebApplicationException {
            outputStream.write(zonedDateTime.toString().getBytes());
        }
    }
}
