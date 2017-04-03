package com.waes.diff;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */

public interface DiffServiceEndpoint {

    @POST
    @Path("{id}/left")
    Response uploadLeft(@PathParam("id") String id,
                        String binaryData);

    @POST
    @Path("{id}/right")
    Response uploadRight(@PathParam("id") String id,
                          String binaryData);

    @GET
    @Path("diff/{id}")
    Response diff(@PathParam("id") String id);
}
