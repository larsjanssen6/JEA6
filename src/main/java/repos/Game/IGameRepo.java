package repos.Game;

import domain.Game.Game;

import java.util.List;

public interface IGameRepo {
    public List<Game> getAll();
    void save(Game game);
}
