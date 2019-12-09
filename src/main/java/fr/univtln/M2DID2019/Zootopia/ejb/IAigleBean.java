package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.vivants.oiseaux.Aigle;

import java.util.List;

public interface IAigleBean {

    List<Aigle> find(String nom);

    List<Aigle> findAll();

    Aigle create(String nom);

    void delete(int nom);

    void update();

    void updateNom(String nom, String ID);
}
