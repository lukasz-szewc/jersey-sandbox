package org.luksze;

import com.sun.net.httpserver.HttpServer;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class JdkResourceTest {

    private WebTarget target;
    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = JdkMain.server();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(JdkMain.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop(0);
    }

    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
