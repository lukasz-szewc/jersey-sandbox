package org.luksze;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JettyMain {
    public static final String BASE_URI = "http://localhost:8080/";
    private static final URI URI = UriBuilder.fromUri(BASE_URI).port(8080).build();

    public static void main(String[] args) throws Exception {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        long startMillis = System.currentTimeMillis();
        Server server = server();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        long time = System.currentTimeMillis() - startMillis;
        System.out.println("Start time: " + time);
        int read = System.in.read();
        server.stop();
    }

    public static Server server() {
        MyResourceConfig myResourceConfig = new MyResourceConfig();
        myResourceConfig.register(ZoneTimeResource.class);
        myResourceConfig.register(new ZoneTimeResource.ZonedDateTimeMessageBodyWriter());
        return JettyHttpContainerFactory.createServer(URI, myResourceConfig, true);
    }

}

