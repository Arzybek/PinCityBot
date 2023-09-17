package kg.arzybek.bots.pincity.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public T persist(T e) {
        em.persist(e);
        return e;
    }

    @Transactional
    public T merge(T e) {
        em.merge(e);
        return e;
    }

}