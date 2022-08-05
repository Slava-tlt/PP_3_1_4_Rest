package ru.kata.spring.boot_security.demo.DAO;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {

        return em.unwrap(Session.class).createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(User user) {
        em.merge(user);

    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }

    @Override
    public User showByEmail(String email) {
        return (User) em.createQuery("select u from User u where u.email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }
}
