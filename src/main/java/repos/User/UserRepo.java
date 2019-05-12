package repos.User;

import domain.User.User;
import interceptor.SimpleInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Stateless
@Interceptors(SimpleInterceptor.class)
public class UserRepo implements IUserRepo {
    @PersistenceContext
    private EntityManager em;

    Validator validator;

    public UserRepo() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    public String save(User person)
    {
        if(getRules(person).isEmpty()) {
            em.persist(person);
            return person.toString();
        }

        else {
            return getRules(person);
        }
    }

    public List<User> all() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public User find(String email) {
        try {
            return em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }

        catch (NoResultException ex) {
            return null;
        }
    }

    public User findById(Long id) {
        try {
            return em.createQuery("select u from User u where u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }

        catch (Exception ex) {
            return null;
        }
    }

    @Transactional(REQUIRED)
    public String update(User user) {
        if(getRules(user).isEmpty()) {
            em.merge(user);
            return user.toString();
        }

        else {
            return getRules(user);
        }
    }

    public boolean login(String email, String password) {
        return find(email).getPassword().equals(password);
    }

    public String getRules(User user)
    {
        StringBuilder lines = new StringBuilder();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        for (ConstraintViolation<User> violation : violations) {
            lines.append(violation.getMessage());
        }

        return lines.toString();
    }
}
