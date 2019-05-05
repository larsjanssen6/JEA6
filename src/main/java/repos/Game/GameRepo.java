package repos.Game;

import domain.Game.Game;
import domain.Game.UserGame;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class GameRepo implements IGameRepo {
    @PersistenceContext
    private EntityManager em;

    public List<UserGame> getAll(long gameId) {
        return em.createQuery("SELECT u FROM UserGame u where u.game.id = :gameId", UserGame.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    @Override
    public void save(Game game) {
        em.persist(game);
    }

    public void delete(long id) {
        Game game = em.find(Game.class, id);
        em.remove(game);
    }
}
