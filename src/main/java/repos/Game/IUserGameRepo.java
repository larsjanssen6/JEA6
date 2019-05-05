package repos.Game;

import domain.Game.UserGame;

import java.util.List;

public interface IUserGameRepo {
    List<UserGame> all(long userId);
    void save(UserGame userGame);
}