package com.example.demo.repository;

import com.example.demo.entity.Hobbies;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class HobbiesRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Hobbies findById(int id) {
        Hobbies hobbies = entityManager.find(Hobbies.class, id);
        return hobbies;
    }

}
