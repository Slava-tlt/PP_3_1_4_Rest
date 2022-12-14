package ru.kata.spring.boot_security.demo.DAO;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDAOImp implements RoleDAO{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Role> getAllRoles() {

        return em.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public Set<Role> findRoles(List<Long> roles) {
        TypedQuery<Role> q = em.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roles);
        return new HashSet<>(q.getResultList());
    }


}
