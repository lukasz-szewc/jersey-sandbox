package org.luksze;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class JettyMain {
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    public static void main(String[] args) throws Exception {
        long startMillis = System.currentTimeMillis();
//        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(MyResource.class);
        long time = System.currentTimeMillis() - startMillis;
        System.out.println("after resource config: " + time);
        Server server = JettyHttpContainerFactory.createServer(URI.create(BASE_URI), config);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        time = System.currentTimeMillis() - startMillis;
        System.out.println("Start time: " + time);
        int read = System.in.read();
        server.stop();
    }
}

