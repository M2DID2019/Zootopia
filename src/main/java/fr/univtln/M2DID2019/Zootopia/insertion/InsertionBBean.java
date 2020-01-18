package fr.univtln.M2DID2019.Zootopia.insertion;

import org.apache.log4j.Logger;
import redis.clients.jedis.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostConstruct
    public void init() {
        recupCles();
    }

    private void recupCles() {
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            ScanParams scanParams = new ScanParams().count(1000);
            Set<String> allKeys = new HashSet<>();
            for (JedisPool pool : jedisCluster.getClusterNodes().values()) {
                String cur = ScanParams.SCAN_POINTER_START;
                do {
                    try (Jedis jedis = pool.getResource()) {
                        ScanResult<String> scanResult = jedis.scan(cur, scanParams);
                        allKeys.addAll(scanResult.getResult());
                        cur = scanResult.getCursor();
                    }
                    if (allKeys.size() >= 1000) break;
                } while (!cur.equals(ScanParams.SCAN_POINTER_START));
                if (allKeys.size() >= 1000) break;
            }
            //allKeys.stream().forEach(System.out::println);
            listeCleRecup = new ArrayList<>(allKeys);
        } catch (Exception e) {logger.error("Erreur: " + e);}
    }

    public void persisteValeur() {
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            jedisCluster.set("K:"+cle, valeur);
            addMessage("Envoie réussi!!!");
            //recupCles();
            if (!listeCleRecup.contains("K:"+cle)) {
                listeCleRecup.add("K:" + cle);
            }
        } catch (Exception e) {logger.error("Erreur: " + e); addMessage("Une erreur s'est produite");}
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
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            valeurRecup =  jedisCluster.get("K:"+cleRecup);
        } catch (Exception e) {logger.error("Erreur: " + e);}
    }

    public void persistePersonne() {
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            Personne p1 = new Personne(nom, age);
            String nomCle = "P:" + p1.getNom();
            jedisCluster.set(nomCle.getBytes(), Personne.serialize(p1));
            addMessage("Envoie réussi!!!");
            //recupCles();
            if (!listeCleRecup.contains(nomCle)) {
                listeCleRecup.add(nomCle);
            }
        } catch (Exception e) {logger.error("Erreur: " + e);}
    }

    public void recupPersonne() {
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            String recup = "P:" + nomRecup;
            personne = Personne.deserialize(jedisCluster.get(recup.getBytes()));
        } catch (Exception e) {logger.error("Erreur: " + e);}
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
