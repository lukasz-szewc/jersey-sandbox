package org.luksze;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdkResourceTest {

    private WebTarget target;
    private HttpServer server;

    @BeforeEach
    void setUp() {
        // start the server
        server = JdkMain.server();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(JdkMain.BASE_URI);
    }

    @AfterEach
    void tearDown() {
        server.stop(0);
    }

    @Test
    void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
