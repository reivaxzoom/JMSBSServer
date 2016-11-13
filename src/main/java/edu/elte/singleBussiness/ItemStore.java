package edu.elte.singleBussiness;

import com.mysema.query.annotations.QueryEntity;
import java.util.Objects;
import javax.money.NumberValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Xavier
 */
@Document(collection = "itemStore")
@TypeAlias("itst")
@QueryEntity
public class ItemStore {
    @Id
    private String id;
    private String name;
    private int amount;
    @Field("money")
    private NumberValue unityPrice;
//    private String Brand;
//    private List<String> listPhotosIt;
    
    
     

    public ItemStore() {
    }

    public ItemStore(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public ItemStore(String name, int amount, NumberValue unityPrice) {
        this.name = name;
        this.amount = amount;
        this.unityPrice = unityPrice; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public NumberValue getUnityPrice() {
        return unityPrice;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemStore other = (ItemStore) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemStore{" + "name=" + name + ", amount=" + amount + ", unityPrice=" + unityPrice + '}';
    }
}
