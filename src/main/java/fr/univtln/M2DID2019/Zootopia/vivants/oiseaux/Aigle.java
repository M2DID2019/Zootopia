package fr.univtln.M2DID2019.Zootopia.vivants.oiseaux;

import fr.univtln.M2DID2019.Zootopia.vivants.Animal;

import javax.persistence.Entity;

@Entity
public class Aigle extends Animal {
    public Aigle() {
    }

    public Aigle(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return super.toString() + "Je suis un aigle.";
    }
}
