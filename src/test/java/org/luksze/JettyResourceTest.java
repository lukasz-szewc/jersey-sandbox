package org.luksze;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_SVG_XML_TYPE;
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

    @Test
    void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    @Test
    void zoneIdGet() {
        String responseMsg = target.path("zoned").request().get(String.class);
        assertEquals("1970-01-01T01:00+01:00[Europe/Warsaw]", responseMsg);
    }

    @Test
    void notAcceptedRequest() {
        //given
        Invocation invocation = target.path("myresource").request(APPLICATION_SVG_XML_TYPE).buildGet();

        //when
        Response response = invocation.invoke();

        //then
        assertEquals(406, response.getStatus());
        assertEquals("My custom text", MyResourceConfig.MY_CUSTOM_TEXT);
    }
}
