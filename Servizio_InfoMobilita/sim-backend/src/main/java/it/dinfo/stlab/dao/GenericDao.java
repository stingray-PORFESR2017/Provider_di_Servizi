package it.dinfo.stlab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDao <T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> typeParameterClass;

    public GenericDao(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T findById(String id){
        return (T) this.entityManager.createQuery("SELECT t FROM " + typeParameterClass.getTypeName() + " t WHERE t.id = :id")
                .setParameter("id",id).getSingleResult();
    }

    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT t FROM " + typeParameterClass.getTypeName() + " t").getResultList();
    }

    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public void update(T entity){
        this.entityManager.merge(entity);
    }

    @Transactional
    public void delete(String uuid){
        this.entityManager.remove(findById(uuid));
    }

}
