package fr.univtln.M2DID2019.Zootopia.jsf;


import fr.univtln.M2DID2019.Zootopia.dao.DaoRedis;
import fr.univtln.M2DID2019.Zootopia.ejb.GestionZoo;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("ZooBBean")
@RequestScoped
public class ZooBBean implements Serializable {
    @EJB private GestionZoo gestionZoo;
    private List<Zoo> zooList;
    @Inject DaoRedis daoRedis;
    private List<String> listeCle;

    public List<Zoo> getAllZoo() {
        zooList = gestionZoo.findAll();
//        System.out.println(zooList);
        return zooList;
//        return gestionZoo.findAll();
    }

    public List<Zoo> getAllZooRedis() {
        listeCle = daoRedis.recupCles();
        List<String> listeCleARecup = recupZoo();
        List<Zoo> listeZoo = new ArrayList<>();
        for (String cle : listeCleARecup) {
            listeZoo.add(Zoo.deserialize(daoRedis.getValeur(cle.getBytes())));
        }
        return listeZoo;
    }

    private List<String> recupZoo() {
        List<String> clePossible = new ArrayList<>();
        for (String cle : listeCle) {
            if (cle.startsWith("Z:")) {
                clePossible.add(cle);
            }
        }
        return clePossible;
    }
}
