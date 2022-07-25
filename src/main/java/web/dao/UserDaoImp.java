package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    @Transactional
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public List<User> getAllUser() {
        return em.createQuery("from User")
                .getResultList();
    }

    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }
}
