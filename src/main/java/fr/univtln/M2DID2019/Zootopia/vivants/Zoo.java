package fr.univtln.M2DID2019.Zootopia.vivants;

import fr.univtln.M2DID2019.Zootopia.annotation.TestCase;
import fr.univtln.M2DID2019.Zootopia.enumeration.CaseMode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Entity
@NamedQuery(name = "findAllZoo", query = "SELECT z FROM Zoo z")
public class Zoo implements Serializable {

    @Getter @Setter @Id @TestCase(CaseMode.UPPER)
    private String nom;

    @Getter @Setter @OneToMany(mappedBy = "zoo")
    private Set<Animal> animaux = new TreeSet<>();

    public void addAnimal(Animal animal){
        this.animaux.add(animal);
        animal.setZoo(this);
    }

    public Zoo(String nom) {
        this.nom = nom;
    }

    public Zoo() {
    }

    @Override
    public String toString() {
        return "Bienvenue au zoo : "+this.getNom()+".";
    }
}
