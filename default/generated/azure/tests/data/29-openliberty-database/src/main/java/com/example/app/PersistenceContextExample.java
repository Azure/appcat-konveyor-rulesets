package com.example.app;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PersistenceContextExample {

    // EntityManager injection using @PersistenceContext
    @PersistenceContext(unitName = "myPU")
    private EntityManager entityManager;
    
    // Another EntityManager injection
    @PersistenceContext(name = "secondaryEM", unitName = "secondaryPU")
    private EntityManager secondaryEntityManager;
    
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }
    
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    
    // Example entity class
    public static class User {
        private Long id;
        private String username;
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getUsername() {
            return username;
        }
        
        public void setUsername(String username) {
            this.username = username;
        }
    }
} 