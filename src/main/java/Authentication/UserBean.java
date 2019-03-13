package Authentication;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT usr FROM User ORDER BY usr.registeredOn ASC", User.class);
        return query.getResultList();
    }

    public void save(User user) {
        em.persist(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public void remove(String email) {
        User user = find(email);
        if (user != null) {
            em.remove(user);
        }
    }

    public void remove(User user) {
        if (user != null && user.getEmail()!=null && em.contains(user)) {
            em.remove(user);
        }
    }

    public User find(String email) {
        return em.find(User.class, email);
    }

    public void detach(User user) {
        em.detach(user);
    }
}