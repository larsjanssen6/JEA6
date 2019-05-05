package repos.Question;

import domain.Question.Question;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class QuestionRepo implements IQuestionRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Question> getAll(long gameId) {
        return em.createQuery("SELECT q FROM Question q where q.game.id = :gameId", Question.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    public void save(Question question) {
        em.persist(question);
    }
}
