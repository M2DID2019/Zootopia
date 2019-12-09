package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.dao.Dao;
import fr.univtln.M2DID2019.Zootopia.vivants.mammiferes.Vache;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class GestionVache implements IVacheBean, IVacheBeanRemote {
    @Inject private Dao dao;

    @Override
    public List<Vache> find(String nom) {
        Map<String, String> mapNom = new HashMap<>();
        mapNom.put("nom", nom);
        return dao.findWithNamedQuery("findVacheByName",mapNom);
    }

    @Override
    public List<Vache> findAll() {
        return dao.findWithNamedQuery("findAllVache");
    }

    @Override
    public Vache create(String nom) {
        return (Vache) dao.create(new Vache(nom));
    }

    @Override
    public void delete(int nom) {
        dao.delete(Vache.class, nom);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateNom(String nom, String ID) {

    }
}
