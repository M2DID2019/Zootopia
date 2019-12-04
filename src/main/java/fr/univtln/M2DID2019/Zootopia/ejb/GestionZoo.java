package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.dao.Dao;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class GestionZoo implements IZooBean, IZooBeanRemote {

    @Inject private Dao dao;
    @Inject private Zoo zoo;

    public Zoo find(String nom){
        return (Zoo) dao.find(zoo.getClass(), nom);
    }

    public List<Zoo> findAll(){
        return dao.findWithNamedQuery("findAllZoo");
    }

    public Zoo create(String nom){
        return (Zoo) dao.create((new Zoo(nom)));
    }

    public void delete(String nom){
        dao.delete(zoo.getClass(), nom);
    }

    public void update(){
        dao.update(zoo);
    }

    public void updateNom(String nom, String ID){
        zoo = (Zoo) dao.find(zoo.getClass(), ID);
        dao.delete(zoo.getClass(),ID);
        zoo.setNom(nom);
        dao.create(zoo);
    }
}
