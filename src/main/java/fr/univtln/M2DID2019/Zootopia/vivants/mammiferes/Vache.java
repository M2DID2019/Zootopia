package fr.univtln.M2DID2019.Zootopia.vivants.mammiferes;

import fr.univtln.M2DID2019.Zootopia.vivants.Animal;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllVache", query = "SELECT v FROM Vache v"),
                @NamedQuery(name = "findVacheByName", query = "SELECT v FROM Vache v WHERE v.nom = :nom")})
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
