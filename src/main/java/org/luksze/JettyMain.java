package org.luksze;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JettyMain {
    public static final String BASE_URI = "http://localhost:8080/";
    public static final URI URI = UriBuilder.fromUri(BASE_URI).port(8080).build();

    public static void main(String[] args) throws Exception {
        long startMillis = System.currentTimeMillis();
//        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        long time = System.currentTimeMillis() - startMillis;
        System.out.println("after resource config: " + time);
        Server server = server();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        time = System.currentTimeMillis() - startMillis;
        System.out.println("Start time: " + time);
        int read = System.in.read();
        server.stop();
    }

    public static Server server() {
        ResourceConfig config = new ResourceConfig(MyResource.class);
        return JettyHttpContainerFactory.createServer(URI, config, true);
    }
}

