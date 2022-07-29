package resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import service.MultiThreading;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "multithreading resource")
@Path("/multithreading")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MultiThreadingResource {

    private final MultiThreading multiThreading;

    @GET
    @ApiOperation("Get Gender")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get gender successfully")})
    public Response getGender() {
        return Response.ok().entity(multiThreading.run()).build();
    }
}
