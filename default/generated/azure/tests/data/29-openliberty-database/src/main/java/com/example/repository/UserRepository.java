package com.example.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.model.User;

@Stateless
public class UserRepository {

    @PersistenceContext(unitName = "myPU")
    private EntityManager em;

    public User findUserById(int id) {
        return em.find(User.class, id);
    }

    public void saveUser(User user) {
        em.persist(user);
    }
    
    public void updateUser(User user) {
        em.merge(user);
    }
    
    public void deleteUser(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }
} 