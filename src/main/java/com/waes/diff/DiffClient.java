package com.waes.diff;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class DiffClient {
    private static final String BASE_URI = "http://localhost:9090/";

    /** end point for read queries */
    private WebTarget var1;


    public DiffClient() {
        Client client = ClientBuilder.newClient();
        var1 = client.target(BASE_URI + "v1/");
    }

    public void uploadLeft() {
        String fileId = "1111";
        WebTarget path = var1.path(fileId+"/left");
        String str = "Nishanthan";
        byte[]   bytesEncoded = Base64.getEncoder().encode(str.getBytes());
        Response post = path.request().post(Entity.entity(bytesEncoded, MediaType.APPLICATION_JSON));
        System.out.println("The left update result :" + post.getStatus() );
    }


    public void uploadRight() {
        String fileId = "1111";
        WebTarget path = var1.path(fileId+"/right");
        String str = "Nishanthan";
        byte[]   bytesEncoded = Base64.getEncoder().encode(str.getBytes());
        Response post = path.request().post(Entity.entity(bytesEncoded, MediaType.APPLICATION_JSON));
        System.out.println("The left update result :" + post.getStatus() );
    }

    void diff(){
        String fileId = "1111";
        WebTarget path = var1.path("diff/"+fileId);

        Response response = path.request().get();
        System.out.println("The result is " + response.readEntity(Result.class));


    }

    public static void main(String[] args) {
        DiffClient diffClient = new DiffClient();
        diffClient.uploadLeft();
        diffClient.uploadRight();
        diffClient.diff();
    }



}
