package fr.univtln.M2DID2019.Zootopia.jsf;

import fr.univtln.M2DID2019.Zootopia.dao.DaoRedis;
import fr.univtln.M2DID2019.Zootopia.ejb.GestionAnimal;
import fr.univtln.M2DID2019.Zootopia.ejb.GestionZoo;
import fr.univtln.M2DID2019.Zootopia.vivants.Animal;
import fr.univtln.M2DID2019.Zootopia.vivants.mammiferes.Vache;
import fr.univtln.M2DID2019.Zootopia.vivants.oiseaux.Aigle;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("AnimalBBean")
@RequestScoped
public class AnimalBBean implements Serializable {

    @EJB
    private GestionAnimal gestionAnimal;
    private List<Animal> animaux;
    @Inject DaoRedis daoRedis;
    private List<String> listeCle;

    public List<Animal> getAllAnimaux() {
        animaux = gestionAnimal.findAll();
        return animaux;
    }

    public List<Animal> getAllAnimauxRedis() {
        listeCle = daoRedis.recupCles();
        List<String> listeCleARecup = recupAnimal();
        List<Animal> listeZoo = new ArrayList<>();
        for (String cle : listeCleARecup) {
            if (cle.startsWith("A:")) {
                listeZoo.add(Aigle.deserialize((daoRedis.getValeur(cle.getBytes()))));
            }
            else {listeZoo.add(Vache.deserialize(daoRedis.getValeur(cle.getBytes())));}
        }
        return listeZoo;
    }

    private List<String> recupAnimal() {
        List<String> clePossible = new ArrayList<>();
        for (String cle : listeCle) {
            if (cle.startsWith("A:") || cle.startsWith("C:")) {
                clePossible.add(cle);
            }
        }
        return clePossible;
    }

}
