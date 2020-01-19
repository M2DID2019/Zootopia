package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.dao.Dao;
import fr.univtln.M2DID2019.Zootopia.dao.DaoRedis;
import fr.univtln.M2DID2019.Zootopia.vivants.oiseaux.Aigle;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class GestionAigle implements IAigleBean, IAigleBeanRemote{
    @Inject private Dao dao;
    @Inject private DaoRedis daoRedis;

    @Override
    public List<Aigle> find(String nom) {
        Map<String, String> mapNom = new HashMap<>();
        mapNom.put("nom", nom);
        return dao.findWithNamedQuery("findAigleByName", mapNom);
    }

    @Override
    public List<Aigle> findAll() {
        return dao.findWithNamedQuery("findAllAigle");
    }

    @Override
    public Aigle create(String nom) {
        return (Aigle) dao.create(new Aigle(nom));
    }

    @Override
    public void delete(int nom) {
        dao.delete(Aigle.class, nom);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateNom(String nom, String ID) {

    }

    // Partie Redis, je garde la partie PostgreSQL si on veut utiliser Redis comme cache

    public Aigle setAigleRedis(Aigle aigle) {
        String cle = "A:" + aigle.getNom();
        daoRedis.setValeur(cle.getBytes(), Aigle.serialize(aigle));
        return aigle;
    }

    public Aigle getZooRedis(String nom) {
        String cle = "A:" + nom;
        return Aigle.deserialize(daoRedis.getValeur(cle.getBytes()));
    }
}
