package fr.univtln.M2DID2019.Zootopia.pubsub;

import org.apache.log4j.Logger;
import org.primefaces.event.TabChangeEvent;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("MessageBBean")
@SessionScoped
public class MessageBBean implements Serializable {
    private final static Logger logger = Logger.getLogger(MessageBBean.class);
    @EJB
    private GestionChannel gestionChannel;
    private String message;
    private String channel;
    private String tabindex;
    private List<String> channel1Message;
    private List<String> channel2Message;
    private List<String> channel3Message;
    private List<JedisPubSub> subscriberList;

    @PostConstruct
    public void init() {
        channel1Message = new ArrayList<>();
        channel2Message = new ArrayList<>();
        channel3Message = new ArrayList<>();
        List<List<String>> listeChannelMessage = new ArrayList<>();
        listeChannelMessage.add(channel1Message);
        listeChannelMessage.add(channel2Message);
        listeChannelMessage.add(channel3Message);
        List<String> listeChannel = new ArrayList<>();
        listeChannel.add("Channel1");
        listeChannel.add("Channel2");
        listeChannel.add("Channel3");
        subscriberList = gestionChannel.subscriptionChannel(listeChannelMessage, listeChannel);
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public void publishMessage() {
        //logger.info("Message: " + message);
        //logger.info("Channel: " + channel);
        gestionChannel.publishOnChannel(channel, message);
        //logger.info("Liste message: " + channel1Message);
    }

    public void onTabChange(TabChangeEvent event) {

    }

    public List<String> getChannel1Message() {
        return channel1Message;
    }

    public List<String> getChannel2Message() {
        return channel2Message;
    }

    public List<String> getChannel3Message() {
        return channel3Message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
