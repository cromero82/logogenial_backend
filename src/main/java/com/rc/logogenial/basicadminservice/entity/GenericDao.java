package com.rc.logogenial.basicadminservice.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {
    private final EntityManager em;

    public GenericDao(EntityManager em) {
        this.em = em;
    }
    public GenericDao(String entity) {
//        EntityManagerFactory entityManagerFactory = Persistence
//                .createEntityManagerFactory("sqlDemoPersistence");
//        this.em = entityManagerFactory.createEntityManager();
        em = Persistence.createEntityManagerFactory("logogenialbd").createEntityManager();
    }

    public EntityManager getEntityManager(){
        return this.em;
    }
}
