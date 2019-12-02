package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.dao.Dao;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

public interface IZooBeanRemote {

    String find(Dao dao, String nom);

    Zoo create(Dao dao, String nom);

    void delete(Dao dao, String nom);

    void update(Dao dao);

    void updateNom(Dao dao, String nom, String ID);
}
