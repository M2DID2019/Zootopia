package fr.univtln.M2DID2019.Zootopia.vivants;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Animal implements Comparable<Animal>{
    @Getter @Setter
    private String nom;

    private static int ID=0;

    @Getter
    private final int id;

    @Getter @Setter
    Zoo zoo;

    @Getter
    private static List<Animal> faune = new ArrayList<>();

    public Animal() {
        id = 0;
    }

    public Animal(String nom) {
        this.nom = nom;
        ID++;
        id = ID;
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
