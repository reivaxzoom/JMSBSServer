package edu.elte.client.threads;

import elte.sportStore.view.Launcher;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.qpid.client.AMQAnyDestination;

/**
 *
 * @author Xavier
 */
public class Suscription {
    
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Suscription.class.getName());
     
    public void addidas() {

        Session session = null;
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);

            javax.jms.ConnectionFactory connectionFactory = (javax.jms.ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Topic requestTopic = session.createTopic("requestTopic");
//            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "addidas");
            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "addidas", Launcher.addidasSelector,true);
            javax.jms.Message message;

            int receivedMsg = 0;
            while ((message = (javax.jms.Message) subscriber1.receive(3000)) != null) {
                System.out.println("\n------------- Msg -------------");
                System.out.println(message);
                System.out.println("-------------------------------\n");
                receivedMsg = receivedMsg + 1;
            }

            session.commit();

//          session.unsubscribe("tesco");       
            session.close();
            connection.close();
//                Assert.assertEquals(numberSampleMessages,receivedMsg);

        } catch (JMSException ex) {
            Logger.getLogger(Suscription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NamingException ex) {
            Logger.getLogger(Suscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    
    public void suscribeReplier()   {
        MessageProducer producer = null;
        Session session = null;
        Connection connection = null;
        log.info("Preparing responses");
        Context context = null;
        try {
         Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("message.properties"));
            context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            
            Destination respQueue = new AMQAnyDestination(Launcher.respQueueName +"; {create: always}");
            producer = session.createProducer(respQueue);

            log.info("Connected to queue " + Launcher.respQueueName);
//                TextMessage m = session.createTextMessage("test");
//                producer.send((javax.jms.Message) m);
        } catch (JMSException | IOException | NamingException |URISyntaxException ex) {
            Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            connection.close();
            session.close();
            producer.close();
            context.close();
            } catch (NamingException | JMSException ex) {
                Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
    
}
