package fr.univtln.M2DID2019.Zootopia.vivants;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Zoo implements Serializable {

    @Getter @Setter @Id
    private String nom;

    @Getter @Setter @OneToMany(mappedBy = "zoo")
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
