package repos.Game;

import domain.Game.UserGame;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserGameRepo implements IUserGameRepo {
    @PersistenceContext
    private EntityManager em;

    public List<UserGame> all(long userId) {
        return em.createQuery("SELECT u FROM UserGame u where u.user.id = :userId", UserGame.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void save(UserGame userGame) {
        em.persist(userGame);
    }
}
