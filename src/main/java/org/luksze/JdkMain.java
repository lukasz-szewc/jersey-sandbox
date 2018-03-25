package org.luksze;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class JdkMain {
    public static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) throws IOException {
        long startMillis = System.currentTimeMillis();
//        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = server();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        long time = System.currentTimeMillis() - startMillis;
        System.out.println("Start time: " + time);
        int read = System.in.read();
        server.stop(1);
    }

    public static HttpServer server() {
        return JdkHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                new ResourceConfig(MyResource.class));
    }
}

