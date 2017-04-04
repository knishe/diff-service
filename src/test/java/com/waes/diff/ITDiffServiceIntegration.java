package com.waes.diff;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;


/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */

@Category(IntegrationTest.class)
public class ITDiffServiceIntegration {

    private static final String BASE_URI = "http://localhost:9090/";
    /** end point for read queries */
    private WebTarget var1;
    @Before
    public void setUp() throws Exception {
        TestWebServer.run();

        Client client = ClientBuilder.newClient();
        var1 = client.target(BASE_URI + "v1/");
    }

    @Test
    @Category(com.waes.diff.IntegrationTest.class)
    public void testWithSameEqualString() {
        String fileId = "1111";
        WebTarget left = var1.path(fileId+"/left");
        Response leftPost = left.request().post(Entity.entity(Base64Utils.getEncodedString("Nishanthan"), MediaType.APPLICATION_JSON));


        WebTarget right = var1.path(fileId+"/right");
        Response rightPost = right.request().post(Entity.entity(Base64Utils.getEncodedString("Nishanthan"), MediaType.APPLICATION_JSON));

        WebTarget diff = var1.path("diff/"+fileId);
        Response diffResponse = diff.request().get();

        Result result = diffResponse.readEntity(Result.class);
        System.out.println(result);

        assertTrue(result.isAreEqual());
        assertTrue(result.isAreSizeEqual());
        assertEquals(null,result.getDiffList());
    }


    @Test
    public void testWithSameSizeDifferentText() {
        String fileId = "2222";
        WebTarget left = var1.path(fileId+"/left");
        Response leftPost = left.request().post(Entity.entity(Base64Utils.getEncodedString("Cat"), MediaType.APPLICATION_JSON));


        WebTarget right = var1.path(fileId+"/right");
        Response rightPost = right.request().post(Entity.entity(Base64Utils.getEncodedString("Dog"), MediaType.APPLICATION_JSON));

        WebTarget diff = var1.path("diff/"+fileId);
        Response diffResponse = diff.request().get();

        Result result = diffResponse.readEntity(Result.class);
        System.out.println(result);

        assertFalse(result.isAreEqual());
        assertTrue(result.isAreSizeEqual());
        assertEquals(3,result.getDiffList().size());
    }

    @Test
    public void testWithDifferentText() {
        String fileId = "3333";
        WebTarget left = var1.path(fileId+"/left");
        Response leftPost = left.request().post(Entity.entity(Base64Utils.getEncodedString("Monkey"), MediaType.APPLICATION_JSON));


        WebTarget right = var1.path(fileId+"/right");
        Response rightPost = right.request().post(Entity.entity(Base64Utils.getEncodedString("Dog"), MediaType.APPLICATION_JSON));

        WebTarget diff = var1.path("diff/"+fileId);
        Response diffResponse = diff.request().get();

        Result result = diffResponse.readEntity(Result.class);
        System.out.println(result);

        assertFalse(result.isAreEqual());
        assertFalse(result.isAreSizeEqual());
        assertEquals(null,result.getDiffList());
    }

    @After
    public void tearDown() throws Exception {
        TestWebServer.stop();
    }
}
