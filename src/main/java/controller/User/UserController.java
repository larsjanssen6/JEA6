package controller.User;

import Jwt.Filter.JWTTokenNeeded;
import domain.User.User;
import repos.User.IUserRepo;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/user")
public class UserController {
    @EJB
    IUserRepo userRepo;

    public UserController(){ }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<User> getAll()
    {
        return userRepo.all();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public User get(@PathParam("userId") long userId)
    {
        return userRepo.findById(userId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response update(User user, @Context HttpServletRequest req)
    {
        User dbUser = userRepo.findById(user.getId());
        user.setPassword(dbUser.getPassword());
        user.setRegisteredOn(new Date());
        userRepo.update(user);
        return Response.ok().entity(user).build();
    }
}
