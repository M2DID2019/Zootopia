package fr.univtln.M2DID2019.Zootopia.dao;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class Dao implements CRUDService {

    @PersistenceContext(unitName = "animaux")
    private EntityManager em;
//    private EntityTransaction transac = em.getTransaction();


    @Override
    public Object create(Object t) {
//        this.transac.begin();
        this.em.persist(t);
        //this.em.flush(); //ecrit direct en base
//        this.transac.commit();
        //this.em.refresh(t); //actualise l objet par rapport a la base
        return t;
    }

    @Override
    public Object find(Class type, Object id) {
        return this.em.find(type, id);
    }

    @Override
    public Object update(Object t) {
        return this.em.merge(t);
    }

    @Override
    public void delete(Class type, Object id) {
//        this.transac.begin();
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
//        this.transac.commit();
    }

    @Override
    public List findWithNamedQuery(String queryName) {
        return this.em.createNamedQuery(queryName).getResultList();
    }

    @Override
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    @Override
    public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    @Override
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Map.Entry<String, String> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}