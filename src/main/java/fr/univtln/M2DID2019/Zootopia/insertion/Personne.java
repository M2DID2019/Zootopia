package fr.univtln.M2DID2019.Zootopia.insertion;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class Personne implements Serializable {
    // Le nom est l'ID
    private String nom;
    private int age;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return this.nom;
    }

    public int getAge() {
        return this.age;
    }

    // En JSONObject: https://stackoverflow.com/questions/44743775/storing-multiple-nested-objects-in-redis

    public static byte[] serialize(Personne personne) {
        return SerializationUtils.serialize(personne);
    }

    public static Personne deserialize(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
