package fr.univtln.M2DID2019.Zootopia.jsf;

import fr.univtln.M2DID2019.Zootopia.ejb.GestionAnimal;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("FormulaireAnimalBBean")
@RequestScoped
public class FormulaireAnimalBBean implements Serializable {
    @EJB
    private GestionAnimal gestionAnimal;

    @Getter @Setter
    private String nom;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private List<String> types;

    public void creer() {
        nom = nom.substring(0,1).toUpperCase()+nom.substring(1).toLowerCase();
        gestionAnimal.create(nom, GestionAnimal.Animaux.valueOf(type));
    }

    @PostConstruct
    public void init() {
        types = new ArrayList<>();
        for(int i = 0; i < GestionAnimal.Animaux.values().length; i++) {
            type = GestionAnimal.Animaux.values()[i].name();
            types.add(type);
        }
    }

}
