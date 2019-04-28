package repos.Game;

import domain.Game.Game;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class GameRepo implements IGameRepo {
    @PersistenceContext
    private EntityManager em;

    public List<Game> getAll() {
        return em.createQuery("SELECT l FROM Game l", Game.class)
                .getResultList();
    }

    @Override
    public void save(Game game) {
        em.persist(game);
    }
}
