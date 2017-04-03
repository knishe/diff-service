package com.waes.diff;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
@Path("/v1")
public class RestDiffServiceEndpoint implements DiffServiceEndpoint{

    private static final Logger LOGGER = Logger.getLogger(RestDiffServiceEndpoint.class.getName());

    @Override
    public Response uploadLeft(String id, String binaryData) {
        return null;
    }

    @Override
    public Response uploadRight(String id, String binaryData) {
        return null;
    }

    @Override
    public Response diff(String id) {
        return Response.status(Response.Status.OK).build();
    }


}
