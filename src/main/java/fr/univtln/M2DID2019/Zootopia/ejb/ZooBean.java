package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.dao.Dao;
import fr.univtln.M2DID2019.Zootopia.vivants.Animal;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class ZooBean implements IZooBean, IZooBeanRemote{

    @Inject Zoo zoo;

    public String find(Dao dao, String nom){
        zoo = (Zoo) dao.find(zoo.getClass(), nom);
        return zoo.toString();
    }

    public Zoo create(Dao dao, String nom){
        zoo.setNom(nom);
        return (Zoo) dao.create(zoo);
    }

    public void delete(Dao dao, String nom){
        dao.delete(zoo.getClass(), nom);
    }

    public void update(Dao dao){
        dao.update(zoo);
    }

    public void updateNom(Dao dao, String nom, String ID){
        zoo = (Zoo) dao.find(zoo.getClass(), ID);
        dao.delete(zoo.getClass(),ID);
        zoo.setNom(nom);
        dao.create(zoo);
    }
}
