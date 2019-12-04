package fr.univtln.M2DID2019.Zootopia.jsf;


import fr.univtln.M2DID2019.Zootopia.ejb.GestionZoo;
import fr.univtln.M2DID2019.Zootopia.vivants.Zoo;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("ZooBBean")
@SessionScoped
public class ZooBBean implements Serializable {
    @EJB private GestionZoo gestionZoo;
    private List<Zoo> zooList;

    public List<Zoo> getAllZoo() {
        zooList = gestionZoo.findAll();
//        System.out.println(zooList);
        return zooList;
//        return gestionZoo.findAll();
    }
}
