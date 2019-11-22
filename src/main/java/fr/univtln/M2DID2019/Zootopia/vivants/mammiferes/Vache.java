package fr.univtln.M2DID2019.Zootopia.vivants.mammiferes;

import fr.univtln.M2DID2019.Zootopia.vivants.Animal;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

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
