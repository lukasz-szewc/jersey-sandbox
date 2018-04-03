package org.luksze;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource/")
public class MyResource {

    private final String string;

    public MyResource(String string) {
        this.string = string;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return string;
    }

    @GET
    @Path("first")
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> listOfIntegers() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("smthing");
        strings.add("abc");
        return strings;
    }

    static class ListOfIntegers implements MessageBodyWriter<List<String>> {
        @Override
        public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return ((ParameterizedTypeImpl) parameterizedType).getRawType().isAssignableFrom(aClass) &&
                    parameterizedType.getActualTypeArguments().length == 1 &&
                    parameterizedType.getActualTypeArguments()[0].equals(String.class);
        }

        @Override
        public void writeTo(List<String> list,
                            Class<?> aClass, Type type,
                            Annotation[] annotations,
                            MediaType mediaType,
                            MultivaluedMap<String, Object> multivaluedMap,
                            OutputStream outputStream) throws IOException, WebApplicationException {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : list) {
                stringBuilder.append(s);
                stringBuilder.append(" - ");
            }
            outputStream.write(stringBuilder.toString().getBytes());
        }
    }
}
