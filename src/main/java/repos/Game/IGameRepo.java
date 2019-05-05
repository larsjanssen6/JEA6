package repos.Game;

import domain.Game.Game;
import domain.Game.UserGame;
import java.util.List;

public interface IGameRepo {
    List<UserGame> getAll(long ownerId);
    void save(Game game);
    void delete(long id);
}
