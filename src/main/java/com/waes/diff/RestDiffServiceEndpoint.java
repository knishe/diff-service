package com.waes.diff;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * A REST implementation of the Diff Service API.
 *
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
@Path("/v1")
public class RestDiffServiceEndpoint implements DiffServiceEndpoint {

    private static final Logger LOGGER = Logger.getLogger(RestDiffServiceEndpoint.class.getName());

    private DiffService diffService;

    public RestDiffServiceEndpoint() {
        diffService = new DiffService();
    }

    public RestDiffServiceEndpoint(DiffService diffService) {
        this.diffService = diffService;
    }

    @Override
    public Response ping() {
        return Response.status(Response.Status.OK).entity("ready").build();
    }

    @Override
    public Response uploadLeft(String id, String binaryData) {
        try {
            diffService.saveLeft(id, binaryData);
        } catch (LeftExistException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            return Response.status(Response.Status.OK)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response uploadRight(String id, String binaryData) {
        try {
            diffService.saveRight(id, binaryData);
        } catch (RightExistException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            return Response.status(Response.Status.OK)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response diff(String id) {
        try {
            Result result = diffService.findDiffById(id);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (ComparisonNotFoundException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            return Response.status(Response.Status.OK)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


}
