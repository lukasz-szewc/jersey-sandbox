package org.luksze;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrizzlyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @BeforeEach
    void setUp() {
        // start the server
        server = GrizzlyMain.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and GrizzlyMain.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(GrizzlyMain.BASE_URI);
    }

    @AfterEach
    void tearDown() {
        server.shutdown();
    }

    @BeforeAll
    static void setUpClass() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
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
