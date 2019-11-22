package fr.univtln.M2DID2019.Zootopia.vivants;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@MappedSuperclass
public abstract class Animal implements Comparable<Animal>, Serializable {
    @Getter @Setter
    private String nom;

//    private static int ID=0;

//    @Getter @Id
//    private final int id;
    // Verifier si le generatedvalue fonctionne
    @Getter @Id @GeneratedValue
    private int id;

    @Getter @Setter @ManyToOne
    private Zoo zoo;

    @Getter
    private static List<Animal> faune = new ArrayList<>();

    public Animal() {
//        id = 0;
    }

    public Animal(String nom) {
        this.nom = nom;
//        ID++;
//        id = ID;
        faune.add(this);
    }

    @Override
    public String toString() {
        return "Je suis un animal nommé " +nom+". ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Animal o) {
        return (id==o.getId())?0:1;
    }
}
