package edu.elte.singleBussiness;

import com.mongodb.Mongo;
import java.net.UnknownHostException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Manage mongo connections  
 * @author Xavier
 */
public class MongoConnection extends Mongo{
    private static MongoConnection instance = null;
    private static MongoOperations mongoOps;

    protected MongoConnection () 
          throws UnknownHostException,UnsupportedOperationException{
    }

    protected MongoConnection (String ip, int port) 
               throws UnknownHostException,UnsupportedOperationException{
        super(ip, port);
    }

    public static synchronized MongoConnection getInstance(String ip, int port) 
                     throws UnknownHostException{
        if (instance == null){
          instance =  new MongoConnection(ip,port);
        }
         return instance;
    }
    public static synchronized MongoOperations getLocalInstance(String dbName) 
                     throws UnknownHostException{

        if (instance == null){
//          instance =  new MongoConnection("192.168.99.100",32768);
          instance =  new MongoConnection("127.0.0.1",27017);
          mongoOps = new MongoTemplate(new SimpleMongoDbFactory(instance, dbName));
        }
         return mongoOps;
    }
}
