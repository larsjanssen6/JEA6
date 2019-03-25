package controller.Authentication.JWT;

import Jwt.Filter.JWTTokenNeeded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/protected")
@Produces(TEXT_PLAIN)
public class SecuredJwtController {

    @GET
    @Path("jwt")
    @JWTTokenNeeded
    public Response secured() {
        return Response.ok().entity("Authenticated!").build();
    }
}
