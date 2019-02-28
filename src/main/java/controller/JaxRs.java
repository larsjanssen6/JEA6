package controller;

import domain.User;
import repos.UserRepo;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/jxrs")
public class JaxRs {

    @EJB
    UserRepo repo;

    @GET
    public User test()
    {
        return repo.all().get(0);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String save(User user)
    {
        return repo.save(user);
    }
}
