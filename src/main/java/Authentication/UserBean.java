package Authentication;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User find(String email) {
        return em.find(User.class, email);
    }

    public void detach(User user) {
        em.detach(user);
    }
}