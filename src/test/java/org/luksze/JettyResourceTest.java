package org.luksze;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JettyResourceTest {

    private Server server;
    private WebTarget target;

    @BeforeEach
    void setUp() {
        // start the server
        this.server = JettyMain.server();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(JettyMain.BASE_URI);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
