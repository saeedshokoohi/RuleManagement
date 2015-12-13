package repository.support;


import domain.BaseEntity;
import domain.Identifiable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by saeed on 13/12/2015.
 */
public class GenericRepository<E extends Identifiable<UUID>> {

    protected Class<E> type;
    @Inject
    EntityManager em;

    public GenericRepository() {
    }

    public GenericRepository(Class<E> type) {
        this.type = type;
    }
    public E findById(UUID id)
    {
        String query = "Select e from " + type.getName() + " e where e.id=:id";
        try {
            return em.createQuery(query, type).setParameter("id", id).getSingleResult();
        }catch (Exception ex)
        {

        }
        return null;
    }
    public List<E> findAll()
    {
        String query = "Select e from " + type.getName() + " e";
        return em.createQuery(query,type).getResultList();
    }
    public E save(E entity)
    {
       if(entity.getId()!=null)entity.setId(UUID.randomUUID());
        em.persist(entity);
        return entity;
    }
    public E update(E entity)
    {
        em.merge(entity);
        return entity;
    }
}
