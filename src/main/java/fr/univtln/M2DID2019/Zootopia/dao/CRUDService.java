package fr.univtln.M2DID2019.Zootopia.dao;

import java.util.List;
import java.util.Map;

public interface CRUDService {
    public Object create(Object t);
    public Object find(Class type,Object id);
    public Object update(Object t);
    public void delete(Class type,Object id);
    public List findWithNamedQuery(String queryName);
    public List findWithNamedQuery(String queryName,int resultLimit);
    public List findWithNamedQuery(String namedQueryName, Map parameters);
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);
}
