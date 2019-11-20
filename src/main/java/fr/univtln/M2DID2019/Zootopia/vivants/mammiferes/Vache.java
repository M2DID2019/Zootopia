package fr.univtln.M2DID2019.Zootopia.vivants.mammiferes;

import fr.univtln.M2DID2019.Zootopia.vivants.Animal;

import javax.persistence.Entity;

@Entity
public class Vache extends Animal {

    public Vache() {
    }

    public Vache(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return super.toString() + "Je suis une vache.";
    }
}
