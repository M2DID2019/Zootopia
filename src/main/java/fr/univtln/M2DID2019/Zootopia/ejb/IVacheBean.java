package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.vivants.mammiferes.Vache;

import java.util.List;

public interface IVacheBean {

    List<Vache> find(String nom);

    List<Vache> findAll();

    Vache create(String nom);

    void delete(int nom);

    void update();

    void updateNom(String nom, String ID);
}
