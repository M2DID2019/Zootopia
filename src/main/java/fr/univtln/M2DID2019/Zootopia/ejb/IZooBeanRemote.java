package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import java.util.List;

public interface IZooBeanRemote {

    Zoo find(String nom);

    List<Zoo> findAll();

    Zoo create(String nom);

    void delete(String nom);

    void update();

    void updateNom(String nom, String ID);
}
