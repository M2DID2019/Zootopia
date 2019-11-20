package fr.univtln.M2DID2019.Zootopia.vivants;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

public class Zoo {

    @Getter @Setter
    private String nom;

    @Getter @Setter
    private static Set<Animal> animaux = new TreeSet<>();

    public void addAnimal(Animal animal){
        this.animaux.add(animal);
    }

    public Zoo(String nom) {
        this.nom = nom;
    }

    public Zoo() {
    }

}
