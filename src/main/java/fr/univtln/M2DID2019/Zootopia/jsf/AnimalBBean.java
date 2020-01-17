package fr.univtln.M2DID2019.Zootopia.jsf;

import fr.univtln.M2DID2019.Zootopia.ejb.GestionAnimal;
import fr.univtln.M2DID2019.Zootopia.ejb.GestionZoo;
import fr.univtln.M2DID2019.Zootopia.vivants.Animal;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("AnimalBBean")
@SessionScoped
public class AnimalBBean implements Serializable {

    @EJB
    private GestionAnimal gestionAnimal;
    private List<Animal> animaux;

    public List<Animal> getAllAnimaux() {
        animaux = gestionAnimal.findAll();
        return animaux;
    }

}
