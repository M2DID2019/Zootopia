package fr.univtln.M2DID2019.Zootopia.ejb;

import fr.univtln.M2DID2019.Zootopia.vivants.Animal;
import fr.univtln.M2DID2019.Zootopia.vivants.mammiferes.Vache;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class GestionAnimal {

    public enum Animaux {
        Aigle, Vache
    }

    @EJB GestionVache gestionVache;
    @EJB GestionAigle gestionAigle;

    public List<Animal> findAll() {
        List<Animal> animaux = new ArrayList<>();
        animaux.addAll(gestionAigle.findAll());
        animaux.addAll(gestionVache.findAll());
        return animaux;
    }

    public Animal create(String nom, Animaux type) {

        if(Animaux.Aigle == type)   return gestionAigle.create(nom);
        else if(Animaux.Vache == type)  return gestionVache.create(nom);
        else return null;
    }

}
