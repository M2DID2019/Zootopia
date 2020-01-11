package fr.univtln.M2DID2019.Zootopia.jsf;

import fr.univtln.M2DID2019.Zootopia.annotation.TestCase;
import fr.univtln.M2DID2019.Zootopia.ejb.GestionZoo;
import fr.univtln.M2DID2019.Zootopia.enumeration.CaseMode;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("FormulaireZooBBean")
@SessionScoped
public class FormulaireZooBBean implements Serializable {
    @EJB
    private GestionZoo gestionZoo;
    // A deplacer apres
//    @TestCase(CaseMode.UPPER)
    private String nom;

    public void afficheMessage() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Succès",  "Le zoo " + nom + " a été ajouté !") );
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        // Ajouter un vérification de la validité du nom côté serveur (model pas controller)
        this.nom = nom;
        gestionZoo.create(nom);
    }
}
