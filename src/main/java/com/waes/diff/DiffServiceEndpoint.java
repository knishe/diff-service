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

    /**
     * A liveliness check for the  endpoint.
     *
     * @return ready when its up and running
     */
    @GET
    @Path("/ping")
    Response ping();

    /**
     * Stores left data for comparision
     *
     * @param id the id of data to be stored
     * @param binaryData the data to be stored on left
     * @return HTTP response
     */
    @POST
    @Path("{id}/left")
    Response uploadLeft(@PathParam("id") String id,
                        String binaryData);

    /**
     * Stored right data for comparison
     *
     * @param id the id of data to stored
     * @param binaryData the data to be stored on right
     * @return HTTP Response
     */
    @POST
    @Path("{id}/right")
    Response uploadRight(@PathParam("id") String id,
                          String binaryData);

    /**
     * Produce the result of comparison as a JSON
     *
     * @param id the id of the comparison information
     * @return an HTTP Response with a json representation of {@link Result}
     */
    @GET
    @Path("diff/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response diff(@PathParam("id") String id);
}
