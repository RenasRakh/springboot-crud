package SpringBoot.Springbootcrud.dao;

import org.springframework.stereotype.Repository;
import SpringBoot.Springbootcrud.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, (long)id);
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("SELECT users FROM User users").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.merge(user));
    }

    @Override
    public void edit(int id, User user) {
        User user2=getById(id);
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());
        user2.setRoles(user.getRoles());
        entityManager.merge(user2);

    }

    @Override
    public User getUserByUserName(String username) {
        User user = entityManager.createQuery(
                "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
        return  user;
    }

    @Override
    public void start(){

    }
}