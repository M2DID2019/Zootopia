package fr.univtln.M2DID2019.Zootopia.dao;

import org.apache.log4j.Logger;
import redis.clients.jedis.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaoRedis implements Serializable {
    private final static Logger logger = Logger.getLogger(DaoRedis.class);

    public List<String> recupCles() {
        List<String> listeCleRecup = null;
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
        return listeCleRecup;
    }

    public int setValeur(String cle, String valeur) {
        int retour = 0;
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            jedisCluster.set(cle, valeur);
            retour = 1;
        } catch (Exception e) {logger.error("Erreur: " + e);}
        return retour;
    }

    public int setValeur(byte[] cle, byte[] valeur) {
        int retour = 0;
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            jedisCluster.set(cle, valeur);
            retour = 1;
        } catch (Exception e) {logger.error("Erreur: " + e);}
        return retour;
    }

    public String getValeur(String cle) {
        String valeurRecup = null;
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            valeurRecup =  jedisCluster.get(cle);
        } catch (Exception e) {logger.error("Erreur: " + e);}
        return valeurRecup;
    }

    public byte[] getValeur(byte[] cle) {
        byte[] valeurRecup = null;
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            valeurRecup =  jedisCluster.get(cle);
        } catch (Exception e) {logger.error("Erreur: " + e);}
        return valeurRecup;
    }
}
