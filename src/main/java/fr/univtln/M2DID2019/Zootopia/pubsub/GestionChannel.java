package fr.univtln.M2DID2019.Zootopia.pubsub;

import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
@LocalBean
public class GestionChannel {
    private final static Logger logger = Logger.getLogger(GestionChannel.class);

    public List<JedisPubSub> subscriptionChannel(List<List<String>> listeListeMessage, List<String> listeChannel) {
        List<JedisPubSub> subscriptionList = new ArrayList<>();
        for (int i = 0; i < listeChannel.size(); i++) {
            subscriptionList.add(createSubscriber(listeChannel.get(i), listeListeMessage.get(i)));
        }
        return subscriptionList;
    }

    private JedisPubSub createSubscriber(String channel, List<String> listeMessage) {
        //try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redis-6379", 6379))) {
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379));
            JedisPubSub subscriber = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    listeMessage.add(message);
                }

                @Override
                public void onSubscribe(String channel, int subscribedChannels) {
                    logger.info("Subscribe to " + channel);
                }

                @Override
                public void onUnsubscribe(String channel, int subscribedChannels) {
                    logger.info("Unsubscribe to " + channel);
                }
            };
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        jedisCluster.subscribe(subscriber, channel);
                        logger.info("Subscription ended.");
                    } catch (Exception e) {
                        logger.error("Subscribing failed.", e);
                    }
                }}).start();
            return subscriber;
        //} catch (Exception e) {logger.error("Erreur: ", e);}
        //return null;
    }

    public void publishOnChannel(String channel, String message) {
        try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("redisMaster1", 6379))) {
            jedisCluster.publish(channel, message);
        } catch (Exception e) {logger.error("Erreur: ", e);}
    }
}
