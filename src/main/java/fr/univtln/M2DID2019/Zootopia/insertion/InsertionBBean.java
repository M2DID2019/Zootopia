package fr.univtln.M2DID2019.Zootopia.insertion;

import fr.univtln.M2DID2019.Zootopia.dao.DaoRedis;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("InsertionBBean")
@SessionScoped
public class InsertionBBean implements Serializable {
    private final static Logger logger = Logger.getLogger(InsertionBBean.class);
    private String cle;
    private String valeur;
    private String cleRecup;
    private String valeurRecup;
    private String nom;
    private String nomRecup;
    private Personne personne;
    private int age;
    private int ageRecup;
    private List<String> listeCleRecup;
    @Inject private DaoRedis daoRedis;

    @PostConstruct
    public void init() {
        listeCleRecup = daoRedis.recupCles();
    }

    public void persisteValeur() {
        int retour = daoRedis.setValeur("K:"+cle, valeur);
        if (retour == 1) {
            addMessage("Envoie réussi!!!");
            if (!listeCleRecup.contains("K:"+cle)) {
                listeCleRecup.add("K:" + cle);
            }
        }
        else {addMessage("Une erreur s'est produite");}
    }

    public List<String> completeRecup(String query) {
        List<String> clePossible = new ArrayList<>();
        for (String cle : listeCleRecup) {
            if (cle.startsWith("K:"+query)) {
                clePossible.add(cle.substring(2));
            }
        }
        return clePossible;
    }

    public List<String> completeRecupPersonne(String query) {
        List<String> clePossible = new ArrayList<>();
        for (String cle : listeCleRecup) {
            if (cle.startsWith("P:"+query)) {
                clePossible.add(cle.substring(2));
            }
        }
        return clePossible;
    }

    public void recupValeur() {
        valeurRecup = daoRedis.getValeur("K:"+cleRecup);
    }

    public void persistePersonne() {
        Personne p1 = new Personne(nom, age);
        String nomCle = "P:" + p1.getNom();
        int retour = daoRedis.setValeur(nomCle.getBytes(), Personne.serialize(p1));
        if (retour == 1) {
            addMessage("Envoie réussi!!!");
            if (!listeCleRecup.contains(nomCle)) {
                listeCleRecup.add(nomCle);
            }
        }
        else {addMessage("Une erreur s'est produite");}
    }

    public void recupPersonne() {
        String recup = "P:" + nomRecup;
        personne = Personne.deserialize(daoRedis.getValeur(recup.getBytes()));
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getNomRecup() {
        return nomRecup;
    }

    public void setNomRecup(String nomRecup) {
        this.nomRecup = nomRecup;
    }

    public int getAgeRecup() {
        return ageRecup;
    }

    public void setAgeRecup(int ageRecup) {
        this.ageRecup = ageRecup;
    }

    public String getValeurRecup() {
        return valeurRecup;
    }

    public void setValeurRecup(String valeurRecup) {
        this.valeurRecup = valeurRecup;
    }

    public String getCleRecup() {
        return cleRecup;
    }

    public void setCleRecup(String cleRecup) {
        this.cleRecup = cleRecup;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
