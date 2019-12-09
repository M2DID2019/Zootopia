package fr.univtln.M2DID2019.Zootopia.vivants.oiseaux;

import fr.univtln.M2DID2019.Zootopia.vivants.Animal;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries( {@NamedQuery(name = "findAllAigle", query = "SELECT a FROM Aigle a"),
                @NamedQuery(name = "findAigleByName", query = "SELECT a FROM Aigle a WHERE a.nom = :nom")})
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
