package controller.Point;

import Jwt.Filter.JWTTokenNeeded;
import domain.Point.Point;
import domain.Point.PointDTO;
import repos.Point.IPointRepo;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/point")
public class PointController {
    @EJB
    IPointRepo pointRepo;

    public PointController(){ }

    @GET
    @Path("/{userId}/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<Point> get(@PathParam("userId") long userId, @PathParam("gameId") long gameId)
    {
        return pointRepo.getAll(userId, gameId);
    }

    @GET
    @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<Point> getAllByGame(@PathParam("gameId") long userId)
    {
        return pointRepo.getAllByGame(userId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @JWTTokenNeeded
    public Response save(PointDTO pointDTO, @Context HttpServletRequest req) {
        Point point = new Point(pointDTO);
        pointRepo.save(point);
        return Response.ok().entity(point).build();
    }
}
