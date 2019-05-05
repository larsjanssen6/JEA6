package controller.Game;

import Jwt.Filter.JWTTokenNeeded;
import domain.Game.UserGame;
import domain.Game.UserGameDTO;
import repos.Game.IUserGameRepo;
import org.json.JSONObject;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/user-game")
public class UserGameController {
    @EJB
    IUserGameRepo userGameRepo;

    public UserGameController(){ }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<UserGame> getAll(@PathParam("userId") long userId)
    {
        return userGameRepo.all(userId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response save(UserGameDTO userGameDTO) {
        UserGame userGame = new UserGame(userGameDTO);
        userGameRepo.save(userGame);

        JSONObject response = new JSONObject();
        response.put("_links",getLinks(URI.create("http://localhost:8080/1/api/user-game/" + Long.toString(userGame.getId()))));
        return Response.ok(response.toString(2)).build();
    }

    /*
      |--------------------------------------------------------------------------
      | Set HATEOS links.
      |--------------------------------------------------------------------------
      */

    private Map<String, URI> getLinks(URI self)
    {
        Map<String, URI> links = new HashMap<>();
        String baseUri = "http://localhost:8080/1";
        links.put("self",self);
        links.put("save",URI.create(baseUri + "/api/user-game/user-id"));
        links.put("get all",URI.create(baseUri + "/api/user-game"));
        return links;
    }
}
