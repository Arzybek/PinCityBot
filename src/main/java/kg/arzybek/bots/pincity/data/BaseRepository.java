package kg.arzybek.bots.pincity.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class BaseRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    public T persist(T e) {
        em.persist(e);
        return e;
    }

}