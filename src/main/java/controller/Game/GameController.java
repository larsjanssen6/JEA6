package controller.Game;

import domain.Game.Game;
import domain.Game.GameDTO;
import repos.Game.IGameRepo;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/game")
public class GameController {
    @EJB
    IGameRepo gameRepo;

    public GameController(){ }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> get()
    {
        return gameRepo.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(GameDTO gameDTO) {
        Game game = new Game(gameDTO);
        game.setCreated_at(new Date());
        gameRepo.save(game);
        return Response.ok().entity(game).build();
    }
}
