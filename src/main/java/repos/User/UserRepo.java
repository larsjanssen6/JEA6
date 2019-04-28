package repos.User;

import domain.User;
import interceptor.SimpleInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

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
            System.out.println("saving user");
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");
            System.out.println(person.toString());
            EntityManager em = entityManagerFactory.createEntityManager();
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
            return em.find(User.class, email);
        }

        catch (Exception ex) {
            return null;
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
