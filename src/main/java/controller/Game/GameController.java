package controller.Game;

import Jwt.Filter.JWTTokenNeeded;
import domain.Game.Game;
import domain.Game.GameDTO;
import domain.Game.UserGame;
import domain.User.User;
import interceptor.SimpleInterceptor;
import repos.Game.IGameRepo;
import repos.Game.IUserGameRepo;
import repos.User.IUserRepo;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/game")
@Interceptors(SimpleInterceptor.class)
public class GameController {
    @EJB
    IGameRepo gameRepo;

    @EJB
    IUserRepo userRepo;

    @EJB
    IUserGameRepo userGameRepo;

    public GameController(){ }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<UserGame> get(@PathParam("id") long id)
    {
        return gameRepo.getAll(id);
    }

    @POST
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @JWTTokenNeeded
    public Response save(GameDTO gameDTO, @PathParam("userId") long userId, @Context HttpServletRequest req) {
        Game game = new Game(gameDTO);
        gameRepo.save(game);

        User user = userRepo.findById(userId);
        UserGame userGame = new UserGame();
        userGame.setGame(game);
        userGame.setUser(user);
        userGameRepo.save(userGame);

        return Response.ok().entity(game).build();
    }

    @DELETE
    @Path("/{id}")
    @JWTTokenNeeded
    public Response delete(@PathParam("id") long id) {
        gameRepo.delete(id);
        return Response.ok(true).build();
    }
}
