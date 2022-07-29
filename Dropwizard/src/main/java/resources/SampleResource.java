package resources;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "health resource")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SampleResource {

    @GET
    @Timed
    public Response Health() {
        return Response.accepted().build();
    }
}
