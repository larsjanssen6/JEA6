package repos.Point;

import domain.Point.Point;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PointRepo implements IPointRepo {
    @PersistenceContext
    private EntityManager em;

    public List<Point> getAll(long userId, long gameId) {
        return em.createQuery("SELECT p FROM Point p where p.game.id = :gameId and p.user.id = :userId", Point.class)
                .setParameter("gameId", gameId)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Point> getAllByGame(long gameId) {
        return em.createQuery("SELECT p FROM Point p where p.game.id = :gameId", Point.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    public void save(Point point) {
        em.persist(point);
    }
}
