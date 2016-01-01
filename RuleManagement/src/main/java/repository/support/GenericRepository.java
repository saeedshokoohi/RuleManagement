package repository.support;


import domain.BaseEntity;
import domain.Identifiable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.applet.AppletContext;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by saeed on 13/12/2015.
 */
@Named
public class GenericRepository<E extends Identifiable<UUID>> {

    protected Class<E> type;
//    @PersistenceContext(name="punit")
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext(name="punit")
    EntityManager em;
    @Autowired
    org.springframework.context.ApplicationContext applicationContext;


    public GenericRepository() {
        //  em= applicationContext.getBean(EntityManager.class);
        int i = 0;
//        if(em==null) em= Persistence.
    }

    public GenericRepository(Class<E> type) {
        if (em == null && entityManagerFactory!=null )
            em = entityManagerFactory.createEntityManager();
        this.type = type;
    }

    public E findById(UUID id) {
        String query = "Select e from " + type.getName() + " e where e.id=:id";
        try {
            return em.createQuery(query, type).setParameter("id", id).getSingleResult();
        } catch (Exception ex) {

        }
        return null;
    }

    public List<E> findAll() {
        String query = "Select e from " + type.getName() + " e";
        return em.createQuery(query, type).getResultList();
    }
    @Transactional
    public E save(E entity) {
        if (entity.getId() == null) entity.setId(UUID.randomUUID());
        em.persist(entity);
        return entity;
    }
    @Transactional
    public E update(E entity) {
        em.merge(entity);
        return entity;
    }
    @Transactional
    public E saveOrUpdate(E entity) {
        E e = findById(entity.getId());
        if(e!=null)
        {
            em.merge(entity);
        }
        else {
            if (entity.getId() == null) entity.setId(UUID.randomUUID());
            em.persist(entity);
        }
        return entity;
    }
}
