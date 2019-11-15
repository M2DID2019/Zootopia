package fr.univtln.M2DID2019.Zootopia.vivants;

import lombok.Getter;
import lombok.Setter;

public abstract class Animal {
    @Setter @Getter
    private String nom;

    public Animal() {
    }

    public Animal(String nom) {
        this.nom = nom;
    }
}
