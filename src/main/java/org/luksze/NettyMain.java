package org.luksze;


import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class NettyMain {
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    public static void main(String[] args) throws IOException {
        long startMillis = System.currentTimeMillis();
//        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig resourceConfig = new ResourceConfig(MyResource.class);
        Channel server = NettyHttpContainerProvider.createServer(URI.create(BASE_URI), resourceConfig, false);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        long time = System.currentTimeMillis() - startMillis;
        System.out.println("Start time: " + time);
        int read = System.in.read();
        server.closeFuture();
    }
}

